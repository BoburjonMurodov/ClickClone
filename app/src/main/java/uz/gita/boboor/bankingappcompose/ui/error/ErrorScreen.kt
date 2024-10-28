package uz.gita.boboor.bankingappcompose.ui.error

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import uz.gita.boboor.bankingappcompose.ui.components.AppButton
import uz.gita.boboor.bankingappcompose.presentation.circleRoundedFont
import uz.gita.boboor.bankingappcompose.ui.theme.backgroundAlpha

class ErrorScreen(private val error: String) : Screen {
    @Composable
    override fun Content() {
        ErrorScreenContent(error)
    }
}

@Composable
fun ErrorScreenContent(error: String = "Xatolik yuz berdi, yana bir bor \nurinib ko'ring") {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundAlpha)
            .padding(24.dp)
    )
    {
        Spacer(modifier = Modifier.weight(1f))

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = error,
            color = Color.White,
            fontSize = 20.sp,
            fontFamily = circleRoundedFont,
            fontWeight = FontWeight.Black,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.weight(0.5f))
        AppButton(text = "OK", enabled = true)
        Spacer(modifier = Modifier.weight(1f))
    }
}

@Composable
@Preview
private fun PreviewErrorScreen() {
    ErrorScreenContent()
}