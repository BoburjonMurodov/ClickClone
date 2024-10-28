package uz.gita.boboor.bankingappcompose.presentation.register

import android.os.Build
import androidx.annotation.RequiresApi
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
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
import uz.gita.boboor.bankingappcompose.ui.components.DatePickerDocked
import uz.gita.boboor.bankingappcompose.ui.components.MyDropDownMenu
import uz.gita.boboor.bankingappcompose.ui.components.TitleText
import uz.gita.boboor.bankingappcompose.ui.theme.background
import uz.gita.boboor.bankingappcompose.ui.theme.blue
import uz.gita.boboor.bankingappcompose.utils.MaskVisualTransformation
import uz.gita.boboor.bankingappcompose.utils.debounceClickable
import uz.gita.common.extensions.hiltScreenModel
import uz.gita.presenter.screens.register.RegisterScreenContract

// RegisterScreen.kt
class RegisterScreen : Screen {
    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    override fun Content() {
        val viewModel: RegisterScreenContract.ViewModel = hiltScreenModel()
        val uiState = viewModel.collectAsState()
        RegisterScreenContent(uiState, viewModel::onEventDispatcher)
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun RegisterScreenContent(
    uiState: State<RegisterScreenContract.UIState>,
    onEventDispatcher: (RegisterScreenContract.Intent) -> Unit
) {
    val focusManager = LocalFocusManager.current
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .background(background)
            .padding(24.dp)
            .fillMaxSize()
            .verticalScroll(scrollState)
            .imePadding()
    ) {
        Header()

        Spacer(Modifier.height(48.dp))

        TitleText(text = "Telefon raqam")

        AppBasicTextField(
            value = uiState.value.phone,
            onValueChanger = {
                if (it.length <= 9) onEventDispatcher(RegisterScreenContract.Intent.OnPhoneChange(it))
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            visualTransformation = MaskVisualTransformation("+998 (##)-###-##-##"),
            error = uiState.value.error
        )

        TitleText(text = "Ism")
        AppBasicTextField(
            value = uiState.value.firstName,
            onValueChanger = { onEventDispatcher(RegisterScreenContract.Intent.OnFirstNameChange(it)) },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        )

        TitleText(text = "Familya")
        AppBasicTextField(
            value = uiState.value.lastName,
            onValueChanger = { onEventDispatcher(RegisterScreenContract.Intent.OnLastNameChange(it)) },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        )

        TitleText(text = "Parol")
        AppPasswordTextField(
            value = uiState.value.password,
            onValueChanger = { onEventDispatcher(RegisterScreenContract.Intent.OnPasswordChange(it)) },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
        )

        TitleText(text = "Jinsi")
        MyDropDownMenu { gender ->
            onEventDispatcher(RegisterScreenContract.Intent.OnGenderChange(gender.number))
        }

        TitleText(text = "Tug'ilgan sana", paddingTop = 16)
        DatePickerDocked { date ->
            onEventDispatcher(RegisterScreenContract.Intent.OnBirthDateChange(date))
        }

        Spacer(modifier = Modifier.weight(1f))

        Spacer(Modifier.height(12.dp))

        AppButton(
            text = "Davom etish",
            enabled = uiState.value.isButtonEnabled,
            isLoading = uiState.value.isLoading,
            onClick = {
                onEventDispatcher(RegisterScreenContract.Intent.ClickSignUpButton)
            }
        )

        Spacer(Modifier.height(12.dp))

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .debounceClickable { onEventDispatcher(RegisterScreenContract.Intent.ClickBackToSignInButton) },
            text = "Hisobingiz bormi? Kiring",
            color = blue,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun Header() {
    Box(Modifier.fillMaxWidth()) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Ro'yhatdan o'tish",
            textAlign = TextAlign.Center,
            color = Color.White,
            fontSize = 20.sp,
            fontFamily = circleRoundedFont,
            fontWeight = FontWeight.SemiBold
        )
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
@Preview
fun PreviewRegisterScreen() {
    val state = remember { mutableStateOf(RegisterScreenContract.UIState()) }
    RegisterScreenContent(state) {}

}

fun String.toPhoneNumber(): String {
    return "+998$this"
}

enum class Gender(val number: String) {
    ERKAK("0"), AYOL("1")
}
