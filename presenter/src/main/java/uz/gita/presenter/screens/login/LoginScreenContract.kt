package uz.gita.presenter.screens.login

import uz.gita.common.extensions.ScreenModelImpl
import uz.gita.presenter.AppViewModel

interface LoginScreenContract {
    @ScreenModelImpl(LoginScreenVM::class)
    interface ViewModel : AppViewModel<UIState, Intent> {
        fun onEventDispatcher(intent: Intent)
    }

    data class UIState(
        val isLoading: Boolean = false,
        val error: String? = null,
        val isSuccess: Boolean = false,
        val phone: String = "",
        val password: String = "" ,
        val isButtonEnabled: Boolean = false
    )

    sealed interface Intent {
        data object ClickLoginButton : Intent
        object ClickSignUp : Intent
        data class OnEnterPhone(val phone: String) : Intent
        data class OnEnterPassword(val password: String) : Intent
    }

    interface Directions {
        suspend fun moveToSignUpScreen()
        suspend fun moveToVerifyScreen(phone: String)
    }
}