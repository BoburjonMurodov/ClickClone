package uz.gita.boboor.bankingappcompose.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.gita.boboor.bankingappcompose.presentation.circleRoundedFont


@Composable
fun TitleText(text: String, paddingTop: Int = 0) {
    Text(
        modifier = Modifier
            .padding(start = 8.dp, top = paddingTop.dp),
        text = text,
        color = Color.White,
        fontSize = 14.sp,
        fontWeight = FontWeight(550),
        fontFamily = circleRoundedFont,
    )
}