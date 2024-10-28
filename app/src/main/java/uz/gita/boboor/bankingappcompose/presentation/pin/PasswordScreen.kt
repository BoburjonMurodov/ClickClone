package uz.gita.boboor.bankingappcompose.presentation.pin

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import org.orbitmvi.orbit.compose.collectAsState
import uz.gita.boboor.bankingappcompose.R
import uz.gita.boboor.bankingappcompose.presentation.circleRoundedFont
import uz.gita.boboor.bankingappcompose.ui.components.PasswordButton
import uz.gita.boboor.bankingappcompose.ui.theme.background
import uz.gita.common.extensions.hiltScreenModel
import uz.gita.common.models.enums.PasswordState
import uz.gita.presenter.screens.pin.PasswordScreenContract

class PasswordScreen(private val passwordState: PasswordState) : Screen {
    @Composable
    override fun Content() {
        val viewModel: PasswordScreenContract.ViewModel = hiltScreenModel()
        viewModel.init(passwordState)
        val state = viewModel.collectAsState()

        PasswordScreenContent(state, viewModel::onEventDispatcher)
    }
}



@Composable
fun PasswordScreenContent(
    state: State<PasswordScreenContract.UIState>,
    onEventDispatcher: (PasswordScreenContract.Intent) -> Unit = {}
) {
    val context = LocalContext.current
    val shakeAnim = remember { Animatable(0f) }

    LaunchedEffect(state.value.showError) {
        if (state.value.showError != PasswordScreenContract.Error.NONE) {
            val vibrator = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                val vibratorManager = context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
                vibratorManager.defaultVibrator
            } else {
                context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            }

            if (vibrator.hasVibrator()) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    val vibrationEffect = VibrationEffect.createOneShot(300, VibrationEffect.DEFAULT_AMPLITUDE)
                    vibrator.vibrate(vibrationEffect)
                } else {
                    vibrator.vibrate(300)
                }
            }

            shakeAnim.animateTo(
                targetValue = 10f,
                animationSpec = tween(50, easing = LinearEasing),
            )
            shakeAnim.animateTo(
                targetValue = -10f,
                animationSpec = tween(50, easing = LinearEasing),
            )
            shakeAnim.animateTo(
                targetValue = 0f,
                animationSpec = tween(50, easing = LinearEasing),
            )
        }
    }

    Column(
        modifier = Modifier
            .background(background)
            .padding(16.dp)
            .fillMaxSize(),
    ) {

        Text(
            modifier = Modifier
                .fillMaxWidth(),
            textAlign = TextAlign.Center,
            text = stringResource(R.string.avtorisation),
            color = Color.White,
            fontSize = 20.sp,
            fontFamily = circleRoundedFont,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(modifier = Modifier.weight(0.05f))

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = when (state.value.passwordState) {
                PasswordState.PASSWORD_NOT_SET -> stringResource(R.string.create_click_pin)
                PasswordState.PASSWORD_VERIFY -> stringResource(R.string.verify_click_pin)
                else -> stringResource(R.string.enter_click_pin)
            },
            textAlign = TextAlign.Center,
            color = Color.White,
            fontFamily = circleRoundedFont,
            fontWeight = FontWeight(500),
            fontSize = 16.sp
        )

        Spacer(modifier = Modifier.weight(0.1f))

        val isPasswordVisible = remember {
            mutableStateOf(false)
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(26.dp)
        ) {

            Box {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.Center)
                        .graphicsLayer {

                        }

                ) {
                    Spacer(modifier = Modifier.weight(0.2f))
                    Icon(
                        modifier = Modifier
                            .height(24.dp)
                            .width(24.dp)
                            .clickable {
                                isPasswordVisible.value = !isPasswordVisible.value
                            },
                        painter = painterResource(id = if (isPasswordVisible.value) R.drawable.ic_eye_visible else R.drawable.ic_eye_hidden),
                        contentDescription = null,
                        tint = Color.White,

                        )
                    Spacer(modifier = Modifier.weight(1.5f))
                }
                Column(
                    modifier = Modifier
                        .matchParentSize()
                        .graphicsLayer {
                            translationX = shakeAnim.value
                        },

                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    PasswordDisplay(
                        password = state.value.password,
                        isPasswordVisible = isPasswordVisible.value
                    )
                }
            }
        }



        Text(
            modifier = Modifier
                .padding(top = 24.dp, bottom = 36.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Center,
            text = when (state.value.showError) {
                PasswordScreenContract.Error.NONE ->" "
                PasswordScreenContract.Error.WRONG_PASSWORD -> stringResource(R.string.wrong_password)
                    PasswordScreenContract.Error.WRONG_VERIFY -> stringResource(R.string.wrong_verify)
            },
            fontSize = 18.sp,
            color = Color(0xFFd27178),
            fontFamily = circleRoundedFont,
            fontWeight = FontWeight.SemiBold
        )

        PasswordKeypad(onEventDispatcher)

        Spacer(modifier = Modifier.weight(0.2f))
    }
}


@Composable
@Preview
private fun PreviewPasswordScreen() {
    val state = remember {
        mutableStateOf(PasswordScreenContract.UIState())
    }
    PasswordScreenContent(state)
}


@Composable
fun PasswordDisplay(
    password: List<Int>,
    isPasswordVisible: Boolean
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        password.forEach { digit ->
            if (isPasswordVisible) {
                Text(
                    text = if (digit == -1) "" else "$digit",
                    color = Color(0xFF0074ff),
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 6.dp)
                )
            } else {
                Icon(
                    painter = painterResource(
                        id = if (digit == -1) R.drawable.ic_outlined_circle else R.drawable.ic_filled_circle
                    ),
                    contentDescription = null,
                    tint = Color(0xFF0074ff),
                    modifier = Modifier
                        .size(20.dp)
                        .padding(2.dp)
                )
            }
        }
    }
}


@Composable
fun PasswordKeypad(onEventDispatcher: (PasswordScreenContract.Intent) -> Unit) {
    var height = remember { mutableStateOf(0.dp) }
    val density = LocalDensity.current
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
    ) {
        items(9) { index ->
            val number = index + 1
            PasswordButton(text = "$number") {
                onEventDispatcher(PasswordScreenContract.Intent.ClickPassword(number))
            }
        }

        item {
            Spacer(modifier = Modifier)
        }

        item {
            PasswordButton(text = "0", modifier = Modifier.onGloballyPositioned {
                with(density) { height.value = it.size.height.toDp() }
            }) {
                onEventDispatcher(PasswordScreenContract.Intent.ClickPassword(0))
            }
        }

        item {
            Button(
                modifier = Modifier.height(height.value),
                onClick = { onEventDispatcher(PasswordScreenContract.Intent.ClickBackspace) },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_backspace),
                    contentDescription = null,
                    modifier = Modifier.size(36.dp)
                )
            }
        }
    }
}
