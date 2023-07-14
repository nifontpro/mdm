package model.response

import kotlinx.serialization.Serializable

@Serializable
data class PageInfo(
	val pageSize: Int = 0,
	val pageNumber: Int = 0,
	val numberOfElements: Int = 0,
	val totalPages: Int = 0,
	val totalElements: Long = 0,
)