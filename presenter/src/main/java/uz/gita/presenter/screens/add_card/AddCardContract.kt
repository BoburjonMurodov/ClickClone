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
        suspend fun moveToAddCardVerify()
    }

    sealed interface Actions {
        data class PanChange(val pan: String) : Actions
        data class NameChange(val name: String) : Actions
        data class DataChange(val date: String) : Actions
        data object AddCard : Actions
    }

    data class UiState(
        val name: String = "",
        val date: String = "",
        val pan: String = ""
    )

    sealed interface SideEffect {

    }

}