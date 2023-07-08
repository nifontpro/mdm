package model.request

import kotlinx.serialization.Serializable

@Serializable
data class GetDeptByIdRequest (
	val authId: Long = 0,
	val deptId: Long = 0
)