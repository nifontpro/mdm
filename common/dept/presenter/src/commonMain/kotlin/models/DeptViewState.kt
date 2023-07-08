package models

import model.Dept

data class DeptViewState(
	val authId: Long = 0,
	val parentId: Long = 0,
	val currentDeptId: Long = 0,
	val onStart: Boolean = true,

	val depts: List<Dept> = emptyList(),

	val success: Boolean = true,
	val errors: List<String> = emptyList()
)