package uz.gita.presenter.screens.main

import uz.gita.common.extensions.ScreenModelImpl
import uz.gita.presenter.AppViewModel


/*
    Created by Boburjon Murodov 29/10/24 at 00:50
*/

interface MainScreenContracts {
    @ScreenModelImpl(MainScreenVM::class)
    interface ViewModel : AppViewModel<UIState, Nothing> {
        fun onEventDispatcher(intent: Intent)
    }

    data class UIState(val initState: Int = -1)

    sealed interface Intent {
        data object OnCLickAddCard : Intent
    }

    interface Directions {
        suspend fun moveToAddCardScreen()
    }
}