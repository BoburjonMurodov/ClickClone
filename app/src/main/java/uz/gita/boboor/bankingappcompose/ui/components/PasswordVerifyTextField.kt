package uz.gita.boboor.bankingappcompose.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import uz.gita.boboor.bankingappcompose.R
import uz.gita.boboor.bankingappcompose.presentation.circleRoundedFont


@Composable
fun PasswordTextField(
    value: String,
    onValueChange: (String) -> Unit,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    onCLickResend: () -> Unit,
    showText: () -> Unit,
    error: String? = null
) {
    Column {
        val borderColor = if (error == null) Color(0xFF3a3a44) else Color.Red
        Column(
            modifier = Modifier
                .padding(top = 8.dp)
                .fillMaxWidth()
                .height(45.dp)
                .border(1.dp, color = borderColor, shape = RoundedCornerShape(10.dp))
                .background(Color(0xFF2a2a32), shape = RoundedCornerShape(10.dp)),
        ) {
            BasicTextField(
                modifier = Modifier
                    .padding(start = 24.dp)
                    .fillMaxWidth(),
                value = value,
                onValueChange = onValueChange,
                textStyle = TextStyle(
                    fontSize = 14.sp,
                    lineHeight = 18.sp,
                    color = Color.White,
                    fontFamily = circleRoundedFont
                ),
                keyboardOptions = keyboardOptions,
                maxLines = 1,
                cursorBrush = SolidColor(Color.White),
                visualTransformation = visualTransformation,
            )
            { innerTextField ->
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.CenterStart
                ) {
                    if (value.isEmpty()) {
                        Text(
                            text = "SMS orqali kelgan kodni kiriting",
                            color = Color(0xFFb3b8ce),
                            fontSize = 14.sp,
                            fontFamily = circleRoundedFont
                        )
                    }

                    Row(
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                            .padding(end = 12.dp)
                    ) {
                        CountDownTimer(
                            onCLickResend = onCLickResend,
                            showText = showText
                        )
                    }

                    innerTextField()
                }
            }
        }
        if (error != null)
            Text(
                modifier = Modifier.padding(4.dp),
                text = error,
                color = Color.Red,
            )

    }
}

@Composable
@Preview
fun PreviewPasswordTextField() {
    Column {

        PasswordTextField(value = "", onValueChange = {}, onCLickResend = { }, showText = { })
        PasswordTextField(value = "", onValueChange = {}, onCLickResend = { }, showText = { }, error = "some error")

    }
}


@Composable
fun CountDownTimer(
    modifier: Modifier = Modifier,
    onCLickResend: () -> Unit,
    showText: () -> Unit
) {
    var timer by remember { mutableStateOf(59) }

    if (timer != 0) {
        Text(
            text = "00:$timer",
            color = Color.White,
            fontSize = 14.sp,
            fontFamily = circleRoundedFont,
            fontWeight = FontWeight.Bold
        )
    } else {
        Icon(
            modifier = Modifier
                .height(24.dp)
                .width(24.dp)
                .clickable {
                    onCLickResend()
                    timer = 59
                },
            painter = painterResource(id = R.drawable.ic_restart),
            contentDescription = null,
            tint = Color(0xFF0673F9)
        )
    }

    LaunchedEffect(key1 = timer) {
        while (timer > 0) {
            delay(1000L)
            timer--
        }
    }
}


