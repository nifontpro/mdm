package model.request

import kotlinx.serialization.Serializable

@Serializable
data class GetUsersByDeptRequest(
	val authId: Long = 0,
	val deptId: Long = 0,
	val baseRequest: BaseRequest? = null
)