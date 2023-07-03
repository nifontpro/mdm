package models

import model.Dept

data class DeptViewState(
	val authId: Long = 0,
	val depts: List<Dept> = emptyList(),
	val selectDeptId: Long = 0,
	val parentId: Long = 0,
	val success: Boolean = true,
	val errors: List<String> = emptyList()
)