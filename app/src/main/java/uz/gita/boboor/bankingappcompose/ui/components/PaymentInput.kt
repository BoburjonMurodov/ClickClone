package uz.gita.boboor.bankingappcompose.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.gita.boboor.bankingappcompose.presentation.circleRoundedFont
import uz.gita.boboor.bankingappcompose.utils.MaskVisualTransformation


@Composable
@Preview
fun PaymentInput(
    value: String = "996930805",
    onValueChanger: (String) -> Unit = {},
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    visualTransformation: VisualTransformation = MaskVisualTransformation("+998 (##) ### ## ##"),
    error: String? = null
) {
    Row(
        modifier = Modifier.height(IntrinsicSize.Max),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(0.7f)) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(45.dp)
                    .border(1.dp, color = Color(0xFF3a3a44), shape = RoundedCornerShape(10.dp))
                    .background(Color(0xFF2a2a32), shape = RoundedCornerShape(10.dp)),
            ) {

                BasicTextField(
                    modifier = Modifier
                        .padding(horizontal = 12.dp)
                        .fillMaxWidth()
                        .align(Alignment.Center),
                    value = value,
                    onValueChange = onValueChanger,
                    textStyle = TextStyle(
                        fontSize = 16.sp,
                        lineHeight = 18.sp,
                        color = Color.White,
                        fontFamily = circleRoundedFont,
                        fontWeight = FontWeight.Medium
                    ),
                    keyboardOptions = keyboardOptions,
                    maxLines = 1,
                    cursorBrush = SolidColor(Color.White),
                    visualTransformation = visualTransformation,
                )

            }
        }

        Spacer(modifier = Modifier.width(8.dp))

        AppButton(
            modifier = Modifier
                .weight(0.3f)
                .fillMaxHeight(),
            text = "To'lash",
            enabled = true
        )
    }
}
