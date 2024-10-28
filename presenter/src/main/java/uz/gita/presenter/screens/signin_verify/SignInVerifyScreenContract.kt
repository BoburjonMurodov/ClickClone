package uz.gita.presenter.screens.signin_verify

import uz.gita.common.extensions.ScreenModelImpl
import uz.gita.presenter.AppViewModel

interface SignInVerifyScreenContract {
    @ScreenModelImpl(SignInVerifyVM::class)
    interface ViewModel : AppViewModel<UIState, SideEffect> {
        fun onEventDispatcher(intent: Intent)
    }

    interface Directions {
        suspend fun moveBack()
        suspend fun moveToPinScreen()
    }

    sealed interface SideEffect {
        data class Toast(val text: String) : SideEffect
    }

    data class UIState(
        val showResend: Boolean = false,
        val isLoading: Boolean = false,
        val error: String? = null
    )

    sealed interface Intent {
        object CLickBack : Intent
        object ClickResend : Intent
        data class ClickButton(val code: String) : Intent
    }
}