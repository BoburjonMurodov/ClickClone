package uz.gita.presenter.screens.register

import uz.gita.common.extensions.ScreenModelImpl
import uz.gita.common.models.request.AuthRequestParam
import uz.gita.presenter.AppViewModel



interface RegisterScreenContract {
    @ScreenModelImpl(RegisterScreenVM::class)
    interface ViewModel : AppViewModel<UIState, SideEffect> {
        fun onEventDispatcher(intent: Intent)
    }

    sealed interface SideEffect

    data class UIState(
        val phone: String = "",
        val firstName: String = "",
        val lastName: String = "",
        val password: String = "",
        val gender: String = "0",
        val birthDate: Long = System.currentTimeMillis(),
        val isLoading: Boolean = false,
        val isButtonEnabled: Boolean = false,
        val error: String? = null
    )

    interface Directions {
        suspend fun moveToVerifyScreen(phone: String)
        suspend fun backToSignInScreen()
    }

    sealed interface Intent {
        object ClickBackToSignInButton : Intent
        object ClickSignUpButton : Intent

        data class OnPhoneChange(val phone: String) : Intent
        data class OnFirstNameChange(val firstName: String) : Intent
        data class OnLastNameChange(val lastName: String) : Intent
        data class OnPasswordChange(val password: String) : Intent
        data class OnGenderChange(val gender: String) : Intent
        data class OnBirthDateChange(val birthDate: Long) : Intent
    }
}
