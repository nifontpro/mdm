package models

import model.Dept

data class DeptViewState(
	val authId: Long,
	val depts: List<Dept>,
	val currentDeptId: Long,
)