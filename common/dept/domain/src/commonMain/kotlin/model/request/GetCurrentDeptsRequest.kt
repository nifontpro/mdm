package model.request

import kotlinx.serialization.Serializable

@Serializable
data class GetCurrentDeptsRequest(
	val authId: Long,
	val parentId: Long,
	val baseRequest: BaseRequest = BaseRequest()
)