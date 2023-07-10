package navigation.test

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp

@Composable
fun TestScreen() {
	val repository by remember {
		mutableStateOf(Repository())
	}

	var items by remember {
		mutableStateOf(emptyList<ListItem>())
	}

	var page by remember {
		mutableStateOf(0)
	}

	LaunchedEffect(true) {
		items = repository.getItems(page, 20)
	}


	InfiniteLoadingList(
		modifier = Modifier.fillMaxSize(),
		items = items,
		loadMore = {
			println("Load...")
			page += 1
			println("Load page = $page")
			items = items + repository.getItems(page, 20)
			println("Load $items")
			println("Load end.")
		},
	) { index, item ->
		Column {
			Text(item.title, color = Color.White, fontSize = 20.sp)
			Text(item.description, color = Color.White, fontSize = 20.sp)
		}

	}

}

// https://stackoverflow.com/questions/66712286/get-last-visible-item-index-in-jetpack-compose-lazycolumn
@Composable
fun <T> InfiniteLoadingList(
	modifier: Modifier,
	items: List<T>,
	loadMore: () -> Unit,
	rowContent: @Composable (Int, T) -> Unit
) {
	val listState = rememberLazyListState()
	val firstVisibleIndex = remember { mutableStateOf(listState.firstVisibleItemIndex) }
	LazyColumn(state = listState, modifier = modifier) {
		itemsIndexed(items) { index, item ->
			rowContent(index, item)
		}
	}
	if (listState.shouldLoadMore(firstVisibleIndex)) {
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