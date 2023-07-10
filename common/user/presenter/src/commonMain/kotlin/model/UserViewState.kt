package model

data class UserViewState(
	val authId: Long = 0,
	val deptId: Long = 0,
	val onStart: Boolean = true,

	val users: List<User> = emptyList(),

	val success: Boolean = true,
	val errors: List<String> = emptyList()
)