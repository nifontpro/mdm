package model.request

import kotlinx.serialization.Serializable

@Serializable
data class GetCurrentDeptsRequest(
	val authId: Long,
	val deptId: Long,
	val baseRequest: BaseRequest = BaseRequest()
)