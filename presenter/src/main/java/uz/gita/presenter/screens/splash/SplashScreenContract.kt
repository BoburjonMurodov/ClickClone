package uz.gita.presenter.screens.splash

import uz.gita.common.extensions.ScreenModelImpl
import uz.gita.common.models.enums.PasswordState
import uz.gita.presenter.AppViewModel


/*
    Created by Boburjon Murodov 19/10/24 at 01:34
*/

interface SplashScreenContract {
    @ScreenModelImpl(SplashScreenVM::class)
    interface ViewModel : AppViewModel<UIState, Nothing> {
        fun onEventDispatcher(intent: Intent)
    }

    data class UIState(
        val initState: Int = -1
    )

    sealed interface Intent {
        data object GetLaunchMode : Intent
    }

    interface Directions {
        suspend fun moveToSignInScreen()
        suspend fun moveToPasswordScreen(passwordState: PasswordState)
    }
}