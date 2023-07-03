package model.request

import kotlinx.serialization.Serializable

@Serializable
data class GetAuthDeptIdRequest(
	val authId: Long = 0,
)