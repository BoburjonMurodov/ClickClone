package uz.gita.boboor.bankingappcompose.presentation.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import org.orbitmvi.orbit.compose.collectAsState
import uz.gita.boboor.bankingappcompose.presentation.circleRoundedFont
import uz.gita.boboor.bankingappcompose.ui.components.AppBasicTextField
import uz.gita.boboor.bankingappcompose.ui.components.AppButton
import uz.gita.boboor.bankingappcompose.ui.components.AppPasswordTextField
import uz.gita.boboor.bankingappcompose.ui.components.TitleText
import uz.gita.boboor.bankingappcompose.ui.theme.background
import uz.gita.boboor.bankingappcompose.ui.theme.blue
import uz.gita.boboor.bankingappcompose.utils.MaskVisualTransformation
import uz.gita.boboor.bankingappcompose.utils.debounceClickable
import uz.gita.common.extensions.hiltScreenModel
import uz.gita.presenter.screens.login.LoginScreenContract

class LoginScreen() : Screen {
    @Composable
    override fun Content() {
        val viewModel: LoginScreenContract.ViewModel = hiltScreenModel()
        LoginScreenContent(viewModel.collectAsState(), viewModel::onEventDispatcher)
    }
}

@Composable
private fun LoginScreenContent(
    state: State<LoginScreenContract.UIState>,
    onEventDispatcher: (LoginScreenContract.Intent) -> Unit = {}
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(background)
            .padding(24.dp)
            .imePadding()

    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Kirish",
            textAlign = TextAlign.Center,
            color = Color.White,
            fontSize = 20.sp,
            fontFamily = circleRoundedFont,
            fontWeight = FontWeight.SemiBold
        )

        Column(
            modifier = Modifier.align(Alignment.Center)

        ) {
            TitleText(text = "Telefon raqamingiz")

            AppBasicTextField(
                value = state.value.phone.ifBlank { "" },
                onValueChanger = {
                    onEventDispatcher.invoke(LoginScreenContract.Intent.OnEnterPhone(it))
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Next),
                visualTransformation = MaskVisualTransformation("+998 (##)-###-##-##"),
                error = state.value.error
            )

            TitleText(text = "Parol")

            AppPasswordTextField(
                value = state.value.password,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                onValueChanger = {
                    onEventDispatcher.invoke(LoginScreenContract.Intent.OnEnterPassword(it))
                }
            )

        }

        Column(modifier = Modifier.align(Alignment.BottomCenter)) {
            AppButton(
                modifier = Modifier.fillMaxWidth(),
                text = "Davom etish",
                enabled = state.value.isButtonEnabled,
                onClick = {
                    onEventDispatcher(LoginScreenContract.Intent.ClickLoginButton)
                },
                isLoading = state.value.isLoading
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .debounceClickable { onEventDispatcher(LoginScreenContract.Intent.ClickSignUp) },
                text = "Hisobingiz yo'qmi? Yangi yarating",
                color = blue,
                textAlign = TextAlign.Center,
            )
        }
    }
}


@Preview
@Composable
private fun PreviewLoginScreen() {
    val state = remember {
        mutableStateOf(LoginScreenContract.UIState())
    }

    LoginScreenContent(state) {}
}