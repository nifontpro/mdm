package navigation.test

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp

private const val PAGE_SIZE = 5

@Composable
fun TestScreenTwo() {
	val repository by remember {
		mutableStateOf(Repository())
	}

	var items by remember {
		mutableStateOf(emptyList<ListItem>())
	}

	var pageInfo by remember {
		mutableStateOf(PageInfo())
	}

	LaunchedEffect(true) {
		items = repository.getItems(pageInfo.page, PAGE_SIZE)
	}

	LazyColumn(
		modifier = Modifier.fillMaxSize()
	) {
		items(items.size) { i ->
			val item = items[i]
			CheckNextItemsLoad(pageInfo = pageInfo, size = items.size, i = i) {
				println("Load...")
				pageInfo = pageInfo.copy(page = pageInfo.page + 1)
				println("Load page = ${pageInfo.page}")
				val newItems = repository.getItems(pageInfo.page, PAGE_SIZE)
				if (newItems.isEmpty()) {
					pageInfo = pageInfo.copy(endReached = true)
					println("Load: End Reached!")
				} else {
					items = items + newItems
				}
				println("Load end.")
			}
			Column {
				Text(item.title, color = Color.White, fontSize = 20.sp)
				Text(item.description, color = Color.White, fontSize = 20.sp)
			}
		}
	}
}

data class PageInfo(
	val page: Int = 0,
	val endReached: Boolean = false,
	val isLoading: Boolean = false,
)

@Composable
fun CheckNextItemsLoad(
	pageInfo: PageInfo,
	size: Int,
	i: Int,
	loadNext: () -> Unit
) {
	LaunchedEffect(
		key1 = size,
		key2 = pageInfo.endReached,
		key3 = pageInfo.isLoading
	) {
		if (i >= size - 1 && !pageInfo.endReached && !pageInfo.isLoading) {
			loadNext()
		}
	}
}