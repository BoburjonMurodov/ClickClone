package uz.gita.presenter.screens.add_card

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.syntax.simple.intent
import javax.inject.Inject

/*
 * Created by mrgladiator on 10/29/24
 */

@HiltViewModel
class AddCardViewModel @Inject constructor(
    val direction: AddCardContract.Direction
) : ViewModel(), AddCardContract.ViewModel {
    override val container = container<AddCardContract.UiState, AddCardContract.SideEffect>(AddCardContract.UiState(""))

    override fun onAction(action: AddCardContract.Actions) = intent {
        when (action) {
            else -> {}
        }
    }
}