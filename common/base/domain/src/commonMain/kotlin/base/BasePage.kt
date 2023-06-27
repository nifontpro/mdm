package base

import kotlinx.serialization.Serializable

@Serializable
data class PageResult<R>(
	val data: List<R>,
	val pageInfo: PageInfo
)

@Serializable
data class PageInfo(
	val pageSize: Int,
	val pageNumber: Int,
	val numberOfElements: Int,
	val totalPages: Int,
	val totalElements: Long,
)