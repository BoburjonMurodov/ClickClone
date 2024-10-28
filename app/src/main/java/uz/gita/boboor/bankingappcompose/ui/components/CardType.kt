package uz.gita.boboor.bankingappcompose.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight.Companion.W600
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.gita.boboor.bankingappcompose.R

/*
 * Created by mrgladiator on 10/29/24
 */

@Composable
fun CardType(modifier: Modifier = Modifier, painter: Painter, name: String, onClick: () -> Unit) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painter, contentDescription = name, modifier = Modifier
                .padding(start = 8.dp)
                .size(30.dp)
        )
        Text(
            text = name,
            modifier = Modifier.padding(start = 16.dp),
            fontSize = 18.sp,
            fontWeight = W600, color = Color.White
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CardTypePreview() {
    MaterialTheme {
        CardType(painter = painterResource(R.drawable.ic_logo_uzairways), name = "So'm karta") {}
        CardType(painter = painterResource(R.drawable.ic_logo_uzairways), name = "So'm karta") {}
    }
}