package uz.gita.boboor.bankingappcompose.presentation.add_card

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight.Companion.W600
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import org.orbitmvi.orbit.compose.collectAsState
import uz.gita.boboor.bankingappcompose.R
import uz.gita.boboor.bankingappcompose.ui.components.AppBasicTextField
import uz.gita.boboor.bankingappcompose.ui.components.AppButton
import uz.gita.boboor.bankingappcompose.ui.components.TitleText
import uz.gita.boboor.bankingappcompose.ui.theme.background
import uz.gita.boboor.bankingappcompose.utils.MaskVisualTransformation
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
    val navigator = LocalNavigator.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(background)
    ) {
        Box(
            modifier = Modifier
                .padding(top = 56.dp, start = 16.dp, bottom = 16.dp)
                .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(R.drawable.ic_back),
                contentDescription = null,
                colorFilter = ColorFilter.tint(Color.White),
                modifier = Modifier
                    .size(16.dp)
                    .align(Alignment.CenterStart)
                    .clickable {
                        navigator?.pop()
                    }
            )
            Text(
                text = "Uzcard/Humo kartasini qo'shish",
                color = Color.White,
                fontWeight = W600,
                fontSize = 18.sp,
                modifier = Modifier.align(Alignment.Center)
            )
        }
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(Color(0xFF2F3448))
                .padding(8.dp)
        ) {
            TitleText(text = "Karta raqami")
            AppBasicTextField(
                value = states.value.pan,
                onValueChanger = {
                    if (it.length <= 16) onActions(AddCardContract.Actions.PanChange(it))
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                visualTransformation = MaskVisualTransformation("#### #### #### ####"),
            )
            TitleText(text = "Karta amal qilish muddati")
            AppBasicTextField(
                value = states.value.date,
                onValueChanger = {
                    if (it.length <= 4) onActions(AddCardContract.Actions.DataChange(it))
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                visualTransformation = MaskVisualTransformation("##/##"),
            )
            TitleText(text = "Karta nomi(shart emas)")
            AppBasicTextField(
                value = states.value.name,
                onValueChanger = {
                    if (it.length <= 16) onActions(AddCardContract.Actions.NameChange(it))
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done
                ),
            )
        }
        Log.d("TTT", "AddCardScreenContent: ${states.value.pan.length >= 16 && states.value.date.length >= 5}")
        Spacer(modifier = Modifier.weight(1f))
        AppButton(
            text = "Qo'shish",
            onClick = {
                onActions(AddCardContract.Actions.AddCard)
            }, enabled = states.value.pan.length >= 16 && states.value.date.length >= 4,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Spacer(Modifier.height(16.dp))
    }
}

@Preview(showBackground = true)
@Composable
private fun AddCardScreenContentPreview() {
    AddCardScreenContent(remember { mutableStateOf(AddCardContract.UiState("")) }, { })
}