package utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import model.response.PageInfo

@Composable
fun CheckNextItemsLoad(
	pageInfo: PageInfo,
	size: Int,
	i: Int,
	isLoading: Boolean = false,
	loadNext: () -> Unit
) {
	LaunchedEffect(
		key1 = size,
		key2 = pageInfo,
		key3 = i
	) {
		// Если в pageInfo будет текущая страница, а не следующая, как сейчас, то условие будет:
		// pageInfo.totalPages > pageInfo.pageNumber + 1
		if (i >= size - 1 && (pageInfo.totalPages > pageInfo.pageNumber) && !isLoading) {
			loadNext()
		}
	}
}