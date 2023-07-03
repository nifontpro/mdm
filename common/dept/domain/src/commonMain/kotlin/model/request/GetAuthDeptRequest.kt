package model.request

import kotlinx.serialization.Serializable

@Serializable
data class GetAuthDeptRequest(
	val authId: Long = 0,
)