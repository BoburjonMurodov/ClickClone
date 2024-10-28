package uz.gita.boboor.bankingappcompose.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.lifecycleScope
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.boboor.bankingappcompose.R
import uz.gita.boboor.bankingappcompose.presentation.splash.SplashScreen
import uz.gita.boboor.bankingappcompose.ui.navigation.NavigationHandler
import uz.gita.boboor.bankingappcompose.ui.theme.background
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigationHandler: NavigationHandler

    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val systemUiController = rememberSystemUiController()
            systemUiController.setSystemBarsColor(background)

            Scaffold(modifier = Modifier
                .fillMaxSize()
                .background(background)) { innerPadding ->
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                ) {
                    Navigator(screen = SplashScreen(), onBackPressed = { true }) { navigator ->
                        navigationHandler.screenState.onEach { it.invoke(navigator) }.launchIn(lifecycleScope)
                        SlideTransition(navigator = navigator)
                    }
                }
            }

        }
    }
}


val circleRoundedFont = FontFamily(
    Font(R.font.circle_rounded_regular_bold, FontWeight.Bold),
    Font(R.font.circle_rounded_regular, FontWeight.Normal)
)

