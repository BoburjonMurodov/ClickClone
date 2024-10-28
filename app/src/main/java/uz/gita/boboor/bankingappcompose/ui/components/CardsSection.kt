package uz.gita.boboor.bankingappcompose.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.snapping.snapFlingBehavior
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uz.gita.common.models.response.CardModel


/*
    Created by Boburjon Murodov 29/10/24 at 01:01
*/
@Composable
fun CardsSection(list: List<CardModel>) {
    val state = rememberLazyListState()
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        state = state,

    ) {
        if (list.isNotEmpty()){
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp)
                        .background(Color.Black)
                )
            }
            items(list.size) {
                Spacer(Modifier.width(24.dp))
                CardItem(list[it])
                Spacer(Modifier.width(24.dp))
            }

            item {
                AddCardSection()
            }
        }

    }
}

@Composable
@Preview
private fun CardsSectionPreview() {
    CardsSection(list = listOf(previewCard, previewCard, previewCard))
}
