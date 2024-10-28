package uz.gita.presenter.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import uz.gita.boboor.bankingappcompose.domain.usecase.SignInUC
import uz.gita.common.extensions.onFailure
import uz.gita.common.extensions.onSuccess
import uz.gita.common.models.request.AuthRequestParam
import uz.gita.presenter.screens.register.toPhoneNumber
import javax.inject.Inject

internal class LoginScreenVM @Inject constructor(
    private val directions: LoginScreenContract.Directions,
    private val loginUseCaseModule: SignInUC
) : LoginScreenContract.ViewModel, ViewModel() {
    private var clickable = true

    override fun onEventDispatcher(intent: LoginScreenContract.Intent) = intent {
        when (intent) {
            is LoginScreenContract.Intent.ClickLoginButton -> {
                if (!clickable) return@intent
                viewModelScope.launch {
                    loginUseCaseModule(AuthRequestParam.SignInRequest(state.phone, state.password))
                        .onStart { clickable = false; reduce { state.copy(isLoading = true) } }
                        .onSuccess { directions.moveToVerifyScreen(phone = state.phone.toPhoneNumber()) }
                        .onFailure { reduce { state.copy(error = it.message) } }
                        .onCompletion { clickable = true; reduce { state.copy(isLoading = false) } }
                        .launchIn(viewModelScope)
                }
            }

            LoginScreenContract.Intent.ClickSignUp -> {
                if (!clickable) return@intent
                directions.moveToSignUpScreen()
            }

            is LoginScreenContract.Intent.OnEnterPassword -> {
                reduce {
                    state.copy(
                        password = intent.password,
                        isButtonEnabled = isInputValid(state.phone, intent.password)
                    )
                }
            }

            is LoginScreenContract.Intent.OnEnterPhone -> {
                val trimmedPhone = intent.phone.filter { it.isDigit() }.take(9)
                reduce {
                    state.copy(
                        phone = trimmedPhone,
                        isButtonEnabled = isInputValid(trimmedPhone, state.password)
                    )
                }
            }
        }
    }


    private fun isInputValid(phone: String, password: String): Boolean {
        return phone.length == 9 && password.length >= 4
    }

    override val container = container<LoginScreenContract.UIState, LoginScreenContract.Intent>(LoginScreenContract.UIState())
}

