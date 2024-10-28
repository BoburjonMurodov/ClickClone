package uz.gita.presenter.screens.pin

import uz.gita.common.extensions.ScreenModelImpl
import uz.gita.common.models.enums.PasswordState
import uz.gita.presenter.AppViewModel


/*
    Created by Boburjon Murodov 19/10/24 at 01:59
*/

interface PasswordScreenContract {
    @ScreenModelImpl(PasswordScreenVM::class)
    interface ViewModel : AppViewModel<UIState, Nothing> {
        fun onEventDispatcher(intent: Intent)
        fun init(passwordState: PasswordState)
    }

    data class UIState(
        val passwordState: PasswordState = PasswordState.PASSWORD_NOT_SET,
        val password: MutableList<Int> = mutableListOf(-1, -1, -1, -1, -1),
        val text: String = "Click Pinni kiriting",
        val showError: Error = Error.NONE,
    )

    sealed interface Intent {
        data class ClickPassword(val pass: Int) : Intent
        data object ClickBackspace : Intent
    }

    interface Directions {
        suspend fun moveToMainScreen()
    }

    enum class Error {
        NONE, WRONG_PASSWORD, WRONG_VERIFY
    }
}