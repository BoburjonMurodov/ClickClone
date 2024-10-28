package uz.gita.boboor.bankingappcompose.presentation.add_card

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import cafe.adriel.voyager.core.screen.Screen
import org.orbitmvi.orbit.compose.collectAsState
import uz.gita.common.extensions.hiltScreenModel
import uz.gita.presenter.screens.add_card.AddCardContract


/*
 * Created by mrgladiator on 10/29/24
 */

object AddCardScreen : Screen {
    private fun readResolve(): Any = AddCardScreen

    @Composable
    override fun Content() {
        val viewModel: AddCardContract.ViewModel = hiltScreenModel()
        AddCardScreenContent(viewModel.collectAsState(), viewModel::onAction)
    }
}

@Composable
private fun AddCardScreenContent(states: State<AddCardContract.UiState>, onActions: (AddCardContract.Actions) -> Unit) {

}

@Preview(showBackground = true)
@Composable
private fun AddCardScreenContentPreview() {
    AddCardScreenContent(remember { mutableStateOf(AddCardContract.UiState("")) }, {})
}