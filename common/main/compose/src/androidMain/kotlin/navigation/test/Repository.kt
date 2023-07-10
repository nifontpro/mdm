package navigation.test

class Repository {

	private val remoteDataSource = (1..100).map {
		ListItem(
			title = "Item $it",
			description = "Description $it"
		)
	}

	fun getItems(page: Int, pageSize: Int): List<ListItem> {
		val startingIndex = page * pageSize
		return if (startingIndex + pageSize <= remoteDataSource.size) {
			remoteDataSource.slice(startingIndex until startingIndex + pageSize)
		} else emptyList()
	}
}