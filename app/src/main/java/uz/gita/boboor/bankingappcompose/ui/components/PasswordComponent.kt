package uz.gita.boboor.bankingappcompose.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.gita.boboor.bankingappcompose.presentation.circleRoundedFont


@Composable
fun PasswordButton(text: String, modifier: Modifier = Modifier, onClick: () -> Unit) {
    Button(
        modifier = modifier.padding(8.dp),
        onClick = onClick,
        shape = RoundedCornerShape(10),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF35353f)
        )

    ) {

        Text(
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 18.dp),
            text = text,
            fontSize = 36.sp,
            fontFamily = circleRoundedFont,
            fontWeight = FontWeight.Bold
        )

    }
}

@Composable
@Preview
private fun PreviewPasswordButton(){
    PasswordButton("1") { }
}
