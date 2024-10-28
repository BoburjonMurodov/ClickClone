package uz.gita.presenter.screens.signin_verify

import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onStart
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import uz.gita.boboor.bankingappcompose.domain.usecase.SignInResendUC
import uz.gita.boboor.bankingappcompose.domain.usecase.SignInVerifyUC
import uz.gita.common.extensions.onFailure
import uz.gita.common.extensions.onSuccess
import uz.gita.common.models.request.AuthRequestParam
import uz.gita.usecase.launchmode.SignedInStateTrueUC
import javax.inject.Inject


internal class SignInVerifyVM @Inject constructor(
    private val directions: SignInVerifyScreenContract.Directions,
    private val signInVerifyUC: SignInVerifyUC,
    private val signInResendUC: SignInResendUC,
    private val signedInStateTrueUC: SignedInStateTrueUC
) : SignInVerifyScreenContract.ViewModel {


    override fun onEventDispatcher(intent: SignInVerifyScreenContract.Intent) = intent {
        when (intent) {
            is SignInVerifyScreenContract.Intent.ClickButton -> {
                signInVerifyUC(AuthRequestParam.SignInVerify(intent.code))
                    .onStart { reduce { state.copy(isLoading = true, error = null) } }
                    .onSuccess {
                        signedInStateTrueUC.invoke()
                        directions.moveToPinScreen()
                    }
                    .onFailure {
                        reduce { state.copy(error = it.message.toString(), isLoading = false) }
                    }
                    .launchIn(screenModelScope)
            }

            SignInVerifyScreenContract.Intent.ClickResend -> signInResendUC().launchIn(screenModelScope)
            SignInVerifyScreenContract.Intent.CLickBack -> directions.moveBack()
        }
    }

    override val container =
        container<SignInVerifyScreenContract.UIState, SignInVerifyScreenContract.SideEffect>(SignInVerifyScreenContract.UIState())
}