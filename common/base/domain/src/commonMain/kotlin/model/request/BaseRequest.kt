package model.request

data class BaseRequest(
	val page: Int? = null,
	val pageSize: Int? = null,
	val filter: String? = null,
	val minDate: Long? = null,
	val maxDate: Long? = null,
	val subdepts: Boolean = false,
	val orders: List<BaseOrder> = emptyList(),
)