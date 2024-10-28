package uz.gita.boboor.bankingappcompose.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uz.gita.boboor.bankingappcompose.R

/*
 * Created by mrgladiator on 10/29/24
 */

@Composable
fun CardType(modifier: Modifier = Modifier, painter: Painter, name: String, onClick: () -> Unit) {
    Row(modifier = modifier) {
        Image(painter = painter, contentDescription = name, modifier = Modifier
            .padding(start = 16.dp)
            .size(30.dp))
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