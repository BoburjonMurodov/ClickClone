package uz.gita.boboor.bankingappcompose.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.gita.boboor.bankingappcompose.presentation.circleRoundedFont
import uz.gita.boboor.bankingappcompose.ui.theme.BankingAppComposeTheme
import uz.gita.boboor.bankingappcompose.utils.debounceClickable


@Composable
fun AppButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit = {},
    enabled: Boolean = false,
    isLoading: Boolean = false
) {

    val enabledColors = Brush.verticalGradient(colors = listOf(Color(0xFF03bffe), Color(0xFF007aff)))
    val disabledColor = Brush.verticalGradient(colors = listOf(Color(0xFF51536a), Color(0xFF51536a)))

    Button(
        modifier = modifier.height(40.dp),
        onClick = {  },
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent
        ),
        shape = RoundedCornerShape(12.dp),
        contentPadding = PaddingValues()
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(12.dp))
                .background(if (enabled) enabledColors else disabledColor)
                .debounceClickable(isLoading = isLoading) { if (enabled) onClick.invoke() }

        ) {
            if (isLoading)
                CircularProgressIndicator(
                    modifier = Modifier
                        .height(20.dp)
                        .width(20.dp),
                    color = Color.White
                )
            else
                Text(
                    modifier = Modifier
                        .padding(12.dp)
                        .align(Alignment.Center),
                    text = text,
                    fontSize = 16.sp,
                    fontFamily = circleRoundedFont,
                    fontWeight = FontWeight.Bold
                )
        }
    }
}

@Composable
@Preview
private fun PreviewAppButton() {
    BankingAppComposeTheme {

        Column {
            AppButton(Modifier.fillMaxWidth(), "Disabled", {}, false)

            Spacer(modifier = Modifier.height(16.dp))

            AppButton(Modifier.fillMaxWidth(), "Enabled", {}, true)

        }
    }
}