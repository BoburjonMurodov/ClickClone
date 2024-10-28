package uz.gita.presenter.screens.singup_verify

import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import uz.gita.boboor.bankingappcompose.domain.usecase.SignUpResendUC
import uz.gita.boboor.bankingappcompose.domain.usecase.SignUpVerifyUC
import uz.gita.common.extensions.onFailure
import uz.gita.common.extensions.onSuccess
import uz.gita.common.models.request.AuthRequestParam
import uz.gita.usecase.launchmode.SignedInStateTrueUC
import javax.inject.Inject


internal class SingUpScreenVerifyVM @Inject constructor(
    private val directions: SignUpVerifyScreenContract.Directions,
    private val signUpVerifyUC: SignUpVerifyUC,
    private val signUpResendUC: SignUpResendUC,
    private val signedInStateTrueUC: SignedInStateTrueUC
) : SignUpVerifyScreenContract.ViewModel {


    override fun onEventDispatcher(intent: SignUpVerifyScreenContract.Intent) = intent {
        when (intent) {

            is SignUpVerifyScreenContract.Intent.ClickButton -> {
                signUpVerifyUC(AuthRequestParam.SignUpVerify(intent.code))
                    .onStart { intent { reduce { state.copy(isLoading = true) } } }
                    .onCompletion { reduce { state.copy(isLoading = false) } }
                    .onSuccess {
                        signedInStateTrueUC.invoke()
                        directions.moveToPinScreen()
                    }
                    .onFailure {
                        reduce { state.copy(error = it.message.toString(), isLoading = false) }
                    }.launchIn(screenModelScope)
            }

            SignUpVerifyScreenContract.Intent.ClickResend -> signUpResendUC().launchIn(screenModelScope)
            SignUpVerifyScreenContract.Intent.CLickBack -> directions.moveBack()
        }
    }

    override val container =
        container<SignUpVerifyScreenContract.UIState, SignUpVerifyScreenContract.SideEffect>(SignUpVerifyScreenContract.UIState())
}