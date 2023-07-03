package models

import model.Dept

data class DeptViewState(
	val depts: List<Dept>,
	val currentDeptId: Long,
)