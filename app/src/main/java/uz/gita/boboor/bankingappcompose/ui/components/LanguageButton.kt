package uz.gita.boboor.bankingappcompose.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.gita.boboor.bankingappcompose.presentation.circleRoundedFont
import uz.gita.boboor.bankingappcompose.ui.theme.BankingAppComposeTheme


@Composable
fun LanguageButton(text: String, onCLickButton: () -> Unit = {}) {
    Button(
        modifier = Modifier.padding(16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent
        ),
        shape = RoundedCornerShape(16.dp),
        contentPadding = PaddingValues(),
        onClick = onCLickButton
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .background(Brush.verticalGradient(colors = listOf(Color(0xFF2DC3F5), Color(0xFF007aff))))
        ) {
            Text(
                modifier = Modifier
                    .padding(18.dp),
                text = text,
                fontSize = 20.sp,
                fontFamily = circleRoundedFont,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Preview
@Composable
fun PreviewGradientButton() {
    BankingAppComposeTheme {
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally)  {
            LanguageButton(text = "Test")
        }
    }
}