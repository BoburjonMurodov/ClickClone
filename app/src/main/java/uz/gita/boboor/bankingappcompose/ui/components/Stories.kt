package uz.gita.boboor.bankingappcompose.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import uz.gita.boboor.bankingappcompose.R
import uz.gita.boboor.bankingappcompose.ui.theme.blue


@Composable
fun Stories() {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(start = 24.dp)

    ) {
        items(7) {
            StoryItem()
            Spacer(modifier = Modifier.width(8.dp))
        }
    }
}

@Composable
fun StoryItem() {
    Box(
        modifier = Modifier
            .width(100.dp)
            .aspectRatio(1f)
            .clip(RoundedCornerShape(16.dp))
            .border(BorderStroke(2.dp, blue), shape = RoundedCornerShape(16.dp))
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
            painter = painterResource(id = R.drawable.img),
            contentDescription = null
        )

    }
}