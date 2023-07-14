package model

import model.response.PageInfo

data class UserViewState(
	val authId: Long = 0,
	val deptId: Long = 0,
	val onStart: Boolean = true,
	val isLoading: Boolean = false,

	val users: List<User> = emptyList(),

	val pageInfo: PageInfo= PageInfo(),

	val success: Boolean = true,
	val errors: List<String> = emptyList()
)