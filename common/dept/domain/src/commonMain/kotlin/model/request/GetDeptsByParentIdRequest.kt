package model.request

import kotlinx.serialization.Serializable

@Serializable
data class GetDeptsByParentIdRequest(
	val authId: Long,
	val parentId: Long,
	val baseRequest: BaseRequest = BaseRequest()
)