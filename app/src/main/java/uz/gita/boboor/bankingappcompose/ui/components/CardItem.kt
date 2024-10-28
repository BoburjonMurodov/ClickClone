package uz.gita.boboor.bankingappcompose.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.gita.boboor.bankingappcompose.R
import uz.gita.boboor.bankingappcompose.presentation.circleRoundedFont
import uz.gita.common.models.response.CardModel


/*
    Created by Boburjon Murodov 29/10/24 at 01:29
*/


@Composable
fun CardItem(card: CardModel) {
    val isAmountVisible = remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .height(120.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(Color(0xff2e2e38))
            .padding(16.dp)
            .padding(top = 8.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Image(
                painter = painterResource(R.drawable.ic_card),
                contentDescription = ""
            )

            Text(
                modifier = Modifier.padding(start = 12.dp),
                text = "**** ${card.pan.takeLast(4)}",
                color = Color(0xFFb5b6cb),
                fontFamily = circleRoundedFont
            )
        }

        Text(
            text = card.name,
            color = Color(0xFFb5b6cb),
            fontFamily = circleRoundedFont
        )

        Spacer(modifier = Modifier.weight(1f))

        Row(verticalAlignment = Alignment.Bottom) {
            if (isAmountVisible.value) {
                Text(
                    text = getAmountSpacer(card.amount.toString()),
                    fontFamily = circleRoundedFont,
                    fontSize = 24.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "  so'm",
                    fontFamily = circleRoundedFont,
                    fontSize = 18.sp,
                    color = Color.White,
                )
            } else {
                Text(
                    text = "*****",
                    fontFamily = circleRoundedFont,
                    fontSize = 24.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(Modifier.weight(1f))

            IconButton( {isAmountVisible.value = !isAmountVisible.value} ) {
                Icon(
                    painter = painterResource(if (isAmountVisible.value) R.drawable.ic_eye_visible else R.drawable.ic_eye_hidden),
                    contentDescription = "",
                    tint = Color.White,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }

        }


    }
}


@Composable
@Preview
private fun CardItemPreview() {
    CardItem(previewCard)
}

fun getAmountSpacer(string: String): String {
    var stringBuilder = StringBuilder(string)

    for (i in string.length - 3 downTo 0 step 3) {
        stringBuilder.insert(i, " ")
    }

    return stringBuilder.toString()
}

val previewCard = CardModel(1_000_000, 0, 9, 2024, true, "Humo", "Murodov Boburjon", "1234 1234 1234 1234", 1)

