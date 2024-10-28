package uz.gita.boboor.bankingappcompose.presentation.language

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import cafe.adriel.voyager.core.screen.Screen
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import org.orbitmvi.orbit.compose.collectAsState
import uz.gita.boboor.bankingappcompose.ui.components.LanguageButton
import uz.gita.boboor.bankingappcompose.ui.theme.background
import uz.gita.common.extensions.hiltScreenModel
import uz.gita.presenter.screens.language.LanguageScreenContract


class LanguageScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel: LanguageScreenContract.ViewModel = hiltScreenModel()
        val uiState = viewModel.collectAsState()
        LanguageScreenContent(uiState.value, viewModel::onEventDispatcher)
    }
}

@Composable
fun LanguageScreenContent(
    state: LanguageScreenContract.UIState,
    onEventDispatcher: (LanguageScreenContract.Intent) -> Unit
) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(background)
    systemUiController.setNavigationBarColor(background)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(background)

    ) {
        Column(
            modifier = Modifier
                .align(Alignment.Center)
        ) {
            LanguageButton("Русский")
            LanguageButton("O'zbek") { onEventDispatcher(LanguageScreenContract.Intent.CLickUzbekButton) }
            LanguageButton("English")
        }
    }
}

@Composable
@Preview
private fun LanguageScreenPreview() {
    val uiState = remember {
        LanguageScreenContract.UIState()
    }
    LanguageScreenContent(uiState, {})
}