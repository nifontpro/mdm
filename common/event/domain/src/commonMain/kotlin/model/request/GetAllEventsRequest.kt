package model.request

import kotlinx.serialization.Serializable

@Serializable
data class GetAllEventsRequest(
	val authId: Long = 0,
	val deptId: Long = 0,
	val baseRequest: BaseRequest = BaseRequest()
)