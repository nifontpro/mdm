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
	) {
		if (i >= size - 1 && (pageInfo.totalPages > pageInfo.pageNumber) && !isLoading) {
			loadNext()
		}
	}
}