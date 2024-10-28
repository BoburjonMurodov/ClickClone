package uz.gita.boboor.bankingappcompose.ui.components

import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import uz.gita.common.models.response.CardModel


/*
    Created by Boburjon Murodov 29/10/24 at 01:01
*/

@Composable
fun CardsSection(list: List<CardModel>) {
    val pageCount = if (list.isNotEmpty()) list.size + 2 else 1
    val pagerState = rememberPagerState {
        pageCount
    }

    HorizontalPager(pagerState) {
        AddCardSection()
    }

}


@Composable
@Preview
private fun CardsSectionPreview() {
    CardsSection(list = emptyList())
}
