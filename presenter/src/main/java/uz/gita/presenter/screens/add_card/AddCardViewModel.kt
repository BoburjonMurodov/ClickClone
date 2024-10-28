package uz.gita.presenter.screens.add_card

import androidx.lifecycle.ViewModel
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import uz.gita.common.models.request.CardRequest
import uz.gita.usecase.card.AddCardUC
import javax.inject.Inject

/*
 * Created by mrgladiator on 10/29/24
 */

class AddCardViewModel @Inject constructor(
    val direction: AddCardContract.Direction,
    private val addCardUC: AddCardUC
) : ViewModel(), AddCardContract.ViewModel {
    override val container = container<AddCardContract.UiState, AddCardContract.SideEffect>(AddCardContract.UiState(""))

    override fun onAction(action: AddCardContract.Actions) = intent {
        when (action) {
            AddCardContract.Actions.AddCard -> {
                addCardUC.invoke(
                    CardRequest.AddCard(
                        state.date.substring(0, 2),
                        expiredYear = state.date.substring(2, 4),
                        name = state.name,
                        pan = state.pan
                    )
                ).collect {
                    it.onSuccess {
                        direction.moveToAddCardVerify()
                    }
                }
            }

            is AddCardContract.Actions.DataChange -> {
                reduce { state.copy(date = action.date) }
            }

            is AddCardContract.Actions.NameChange -> {
                reduce { state.copy(name = action.name) }
            }

            is AddCardContract.Actions.PanChange -> {
                reduce { state.copy(pan = action.pan) }
            }
        }
    }
}