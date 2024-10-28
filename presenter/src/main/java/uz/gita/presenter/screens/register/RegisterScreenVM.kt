package uz.gita.presenter.screens.register

import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import uz.gita.boboor.bankingappcompose.domain.usecase.SignUpUC
import uz.gita.common.extensions.onFailure
import uz.gita.common.extensions.onSuccess
import uz.gita.common.models.request.AuthRequestParam
import javax.inject.Inject


// RegisterScreenVM.kt
internal class RegisterScreenVM @Inject constructor(
    private val directions: RegisterScreenContract.Directions,
    private val signUpUC: SignUpUC
) : RegisterScreenContract.ViewModel {
    private var clickable = true

    override val container =
        container<RegisterScreenContract.UIState, RegisterScreenContract.SideEffect>(
            RegisterScreenContract.UIState()
        )

    override fun onEventDispatcher(intent: RegisterScreenContract.Intent) = intent {
        when (intent) {
            RegisterScreenContract.Intent.ClickBackToSignInButton -> {
                if (!clickable) return@intent
                directions.backToSignInScreen()
            }

            RegisterScreenContract.Intent.ClickSignUpButton -> {
                if (!clickable) return@intent
                signUpUC(
                    AuthRequestParam.SignUpRequest(
                        phone = state.phone.toPhoneNumber(),
                        password = state.password,
                        firstName = state.firstName,
                        lastName = state.lastName,
                        gender = state.gender,
                        birthDate = state.birthDate.toString()
                    )
                )
                    .onStart { clickable = false;reduce { state.copy(isLoading = true) } }
                    .onCompletion { clickable = true; reduce { state.copy(isLoading = false) } }
                    .onSuccess { directions.moveToVerifyScreen(state.phone.toPhoneNumber()) }
                    .onFailure { reduce { state.copy(error = it.message) } }
                    .launchIn(screenModelScope)
            }

            is RegisterScreenContract.Intent.OnPhoneChange -> reduce { state.copy(phone = intent.phone, isButtonEnabled = isFormValid(state)) }

            is RegisterScreenContract.Intent.OnFirstNameChange ->
                reduce { state.copy(firstName = intent.firstName, isButtonEnabled = isFormValid(state)) }

            is RegisterScreenContract.Intent.OnLastNameChange ->
                reduce { state.copy(lastName = intent.lastName) }

            is RegisterScreenContract.Intent.OnPasswordChange ->
                reduce { state.copy(password = intent.password, isButtonEnabled = isFormValid(state)) }

            is RegisterScreenContract.Intent.OnGenderChange ->
                reduce { state.copy(gender = intent.gender) }

            is RegisterScreenContract.Intent.OnBirthDateChange ->
                reduce { state.copy(birthDate = intent.birthDate) }
        }
    }

    private fun isFormValid(state: RegisterScreenContract.UIState) =
        state.phone.length == 9 && state.firstName.isNotEmpty() && state.password.length >= 4
}

fun String.toPhoneNumber(): String {
    return "+998$this"
}