package mappers

import biz.proc.ContextState
import biz.proc.DeptCommand
import biz.proc.DeptContext
import models.DeptViewState

fun DeptContext.toDeptViewState(ignoreSuccess: Boolean = false) = DeptViewState(
	depts = depts,
	authId = authId,
	currentDeptId = currentDeptId,
	parentId = parentDeptId,
	onStart = onStart,
	success = if (ignoreSuccess) true else state == ContextState.FINISHING,
	errors = errors.map { it.message }
)

fun DeptViewState.toDeptContext(
	command: DeptCommand,
	clickDeptId: Long
) = DeptContext(
	command = command,
	depts = depts,
	authId = authId,
	currentDeptId = currentDeptId,
	parentDeptId = parentId,
	onStart = onStart,
	clickDeptId = clickDeptId
)

