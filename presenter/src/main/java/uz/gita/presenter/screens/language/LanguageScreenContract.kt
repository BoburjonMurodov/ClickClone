package uz.gita.presenter.screens.language
import uz.gita.presenter.AppViewModel
import uz.gita.common.extensions.ScreenModelImpl


interface LanguageScreenContract {
    @ScreenModelImpl(LanguageVM::class)
    interface ViewModel : AppViewModel<UIState, SideEffect> {
        fun onEventDispatcher(intent: Intent)
    }

    data class UIState(
        var onClickUzbekButton: Boolean = false
    )

    sealed interface SideEffect

    interface Direction {
        suspend fun moveToSignInScreen()
    }

    interface Intent {
        object CLickUzbekButton : Intent
    }
}