package navigation.test

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import utils.InfiniteLoadingList

private const val PAGE_SIZE = 20

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

	val endReached = remember {
		mutableStateOf(false)
	}

	LaunchedEffect(true) {
		items = repository.getItems(page, PAGE_SIZE)
	}


	InfiniteLoadingList(
		modifier = Modifier.fillMaxSize(),
		items = items,
		loadMore = {
			println("Load...")
			page += 1
			println("Load page = $page")
			val newItems = repository.getItems(page, PAGE_SIZE)
			if (newItems.isEmpty()) {
				endReached.value = true
				println("Load: End Reached!")
			} else {
				items = items + newItems
			}
			println("Load $items")
			println("Load end.")
		},
		endReached = endReached
	) { _, item ->
		Column {
			Text(item.title, color = Color.White, fontSize = 20.sp)
			Text(item.description, color = Color.White, fontSize = 20.sp)
		}
	}

}