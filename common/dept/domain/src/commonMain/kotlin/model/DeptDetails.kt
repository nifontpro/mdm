package model

data class DeptDetails(
	val dept: Dept = Dept(),
	val address: String? = null,
	val email: String? = null,
	val phone: String? = null,
	val description: String? = null,
	val createdAt: Long? = null,
)
