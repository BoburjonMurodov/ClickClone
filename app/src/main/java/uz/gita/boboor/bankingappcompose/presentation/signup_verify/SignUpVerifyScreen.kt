package uz.gita.boboor.bankingappcompose.presentation.signup_verify

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import org.orbitmvi.orbit.compose.collectAsState
import uz.gita.boboor.bankingappcompose.R
import uz.gita.boboor.bankingappcompose.presentation.circleRoundedFont
import uz.gita.boboor.bankingappcompose.ui.components.AppButton
import uz.gita.boboor.bankingappcompose.ui.components.PasswordTextField
import uz.gita.boboor.bankingappcompose.ui.theme.background
import uz.gita.boboor.bankingappcompose.ui.theme.blue
import uz.gita.common.extensions.hiltScreenModel
import uz.gita.presenter.screens.singup_verify.SignUpVerifyScreenContract

class SignUpVerifyScreen(private val phone: String) : Screen {
    @Composable
    override fun Content() {
        val viewModel: SignUpVerifyScreenContract.ViewModel = hiltScreenModel()
        val uiState = viewModel.collectAsState()
        VerifyScreenContent(phone, uiState, viewModel::onEventDispatcher)
    }
}

@Composable
private fun VerifyScreenContent(
    phone: String,
    uiState: State<SignUpVerifyScreenContract.UIState>,
    onEventDispatcher: (SignUpVerifyScreenContract.Intent) -> Unit
) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(background)

    var code by remember { mutableStateOf("") }
    var showResendText by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF252429))
            .padding(24.dp)
            .imePadding()

    ) {
        Box(Modifier.fillMaxWidth()) {
            Icon(
                modifier = Modifier
                    .height(24.dp)
                    .width(24.dp)
                    .clickable { onEventDispatcher(SignUpVerifyScreenContract.Intent.CLickBack) },

                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = null,
                tint = Color.White
            )

            Text(
                modifier = Modifier
                    .align(Alignment.Center),
                text = "Avtorizatsiya",
                color = Color.White,
                fontFamily = circleRoundedFont,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )


            Icon(
                modifier = Modifier
                    .height(20.dp)
                    .width(20.dp)
                    .align(Alignment.CenterEnd),

                painter = painterResource(id = R.drawable.ic_question),
                contentDescription = null,
                tint = Color(0xFF0673F9)
            )
        }

        Spacer(modifier = Modifier.weight(0.2f))

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Sms kod $phone raqamiga \nyuborildi",
            color = Color.White,
            textAlign = TextAlign.Center,
            fontSize = 14.sp,
            fontFamily = circleRoundedFont,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        PasswordTextField(
            value = code,
            onValueChange = {
                if (it.length <= 6) code = it
//                if (it.length == 6) onEventDispatcher(SignUpVerifyScreenContract.Intent.ClickButton(code))
            },
            onCLickResend = {
                SignUpVerifyScreenContract.Intent.ClickResend
                showResendText = false
            },
            showText = { showResendText = true },
            error = uiState.value.error
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (showResendText)
            Text(
                text = "SMS orqali kod yetib kelmadimi?",
                color = blue
            )

        Spacer(modifier = Modifier.weight(0.7f))

        AppButton(
            text = "Davom etish",
            onClick = { onEventDispatcher(SignUpVerifyScreenContract.Intent.ClickButton(code)) },
            enabled = code.length == 6,
            isLoading = uiState.value.isLoading
        )

    }
}

@Preview
@Composable
private fun VerifyScreenPreview() {
    val uiState = remember {
        mutableStateOf(SignUpVerifyScreenContract.UIState())
    }

    val phone = "+998 99 693 08 05"
    VerifyScreenContent(phone, uiState, {})
}
