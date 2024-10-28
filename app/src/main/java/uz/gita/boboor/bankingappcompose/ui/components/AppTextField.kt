package uz.gita.boboor.bankingappcompose.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.gita.boboor.bankingappcompose.R
import uz.gita.boboor.bankingappcompose.presentation.circleRoundedFont


@Composable
fun AppBasicTextField(
    value: String,
    onValueChanger: (String) -> Unit,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    error: String? = null
) {
    Column {
        Box(
            modifier = Modifier
                .padding(top = 8.dp)
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

        }

        Text(
            modifier = Modifier.padding(4.dp),
            text = if (error != null) error else "",
            color = Color.Red,
            fontSize = 12.sp,
        )

    }
}

@Composable
@Preview
private fun PreviewAppBasicTextField() {
    AppBasicTextField(value = "", onValueChanger = {}, error = "this is error")
}



@Composable
fun AppPasswordTextField(
    value: String,
    onValueChanger: (String) -> Unit,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    error: String? = null
) {
    var isPasswordVisible by remember { mutableStateOf(false) }

    Column {
        Box(
            modifier = Modifier
                .padding(top = 8.dp)
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
                    fontSize = 14.sp,
                    lineHeight = 18.sp,
                    color = Color.White,
                    fontFamily = circleRoundedFont
                ),
                keyboardOptions = keyboardOptions,
                maxLines = 1,
                cursorBrush = SolidColor(Color.White),
                visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation()
            )

            // Eye icon to toggle password visibility
            IconButton(
                onClick = { isPasswordVisible = !isPasswordVisible },
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(end = 12.dp)
            ) {
                Icon(
                    painter = if (isPasswordVisible) painterResource(R.drawable.ic_eye_visible) else painterResource(R.drawable.ic_eye_hidden),
                    contentDescription = "Toggle password visibility",
                    tint = Color.White
                )
            }
        }

        Text(
            modifier = Modifier.padding(4.dp),
            text = if (error != null) error else "",
            color = Color.Red,
            fontSize = 12.sp,
        )
    }
}


@Composable
@Preview
private fun PreviewAppBasicPasswordField() {
    AppPasswordTextField(value = "", onValueChanger = {}, error = "this is error")
}
