package uz.gita.presenter.screens.add_card

import uz.gita.common.extensions.ScreenModelImpl
import uz.gita.presenter.AppViewModel


/*
 * Created by mrgladiator on 10/29/24
 */

interface AddCardContract {

    @ScreenModelImpl(AddCardViewModel::class)
    interface ViewModel : AppViewModel<UiState, SideEffect> {
        fun onAction(action: Actions)
    }

    interface Direction {

    }

    sealed interface Actions {

    }

    data class UiState(
        val name: String = "",
        val date: String = "",
        val pan: String = ""
    )

    sealed interface SideEffect {

    }

}