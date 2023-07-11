package utils

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

// https://stackoverflow.com/questions/66712286/get-last-visible-item-index-in-jetpack-compose-lazycolumn
@Composable
fun <T> InfiniteLoadingList(
	modifier: Modifier,
	verticalArrangement: Arrangement.Vertical = Arrangement.Top,
	horizontalAlignment: Alignment.Horizontal = Alignment.Start,
	items: List<T>,
	loadMore: () -> Unit,
	endReached: MutableState<Boolean> = mutableStateOf(false),
	rowContent: @Composable (Int, T) -> Unit
) {
	val listState = rememberLazyListState()
	val firstVisibleIndex = remember { mutableStateOf(listState.firstVisibleItemIndex) }
	LazyColumn(
		state = listState, modifier = modifier,
		verticalArrangement = verticalArrangement,
		horizontalAlignment = horizontalAlignment,
	) {
		itemsIndexed(items) { index, item ->
			rowContent(index, item)
		}
	}
	if (listState.shouldLoadMore(firstVisibleIndex) && !endReached.value) {
		loadMore()
	}
}

fun LazyListState.shouldLoadMore(rememberedIndex: MutableState<Int>): Boolean {
	val firstVisibleIndex = this.firstVisibleItemIndex
	if (rememberedIndex.value != firstVisibleIndex) {
		rememberedIndex.value = firstVisibleIndex
		return layoutInfo.visibleItemsInfo.lastOrNull()?.index == layoutInfo.totalItemsCount - 1
	}
	return false
}