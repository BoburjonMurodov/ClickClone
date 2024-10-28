package uz.gita.boboor.bankingappcompose.presentation.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import org.orbitmvi.orbit.compose.collectAsState
import uz.gita.boboor.bankingappcompose.R
import uz.gita.boboor.bankingappcompose.ui.theme.background
import uz.gita.boboor.bankingappcompose.ui.theme.blue
import uz.gita.common.extensions.hiltScreenModel
import uz.gita.presenter.screens.splash.SplashScreenContract


/*
    Created by Boburjon Murodov 18/10/24 at 17:40
*/

class SplashScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel: SplashScreenContract.ViewModel = hiltScreenModel()
        val state = viewModel.collectAsState()
        SplashScreenContent(state, viewModel::onEventDispatcher)
    }
}

@Composable
fun SplashScreenContent(
    state: State<SplashScreenContract.UIState>,
    onEventDispatcher: (SplashScreenContract.Intent) -> Unit = {}
) {
    LaunchedEffect(Unit) {
        onEventDispatcher.invoke(SplashScreenContract.Intent.GetLaunchMode)
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(background)
    ) {
        Icon(
            modifier = Modifier
                .size(150.dp)
                .align(Alignment.Center),
            painter = painterResource(id = R.drawable.click_splash_logo_psd),
            contentDescription = null,
            tint = blue
        )
    }
}


@Composable
@Preview
fun PreviewSplashScreen() {
    val state = remember {
        mutableStateOf(SplashScreenContract.UIState())
    }

    SplashScreenContent(state) {}
}
