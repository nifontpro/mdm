package model.request

import kotlinx.serialization.Serializable

@Serializable
data class GetAuthParentIdRequest(
	val authId: Long = 0,
)