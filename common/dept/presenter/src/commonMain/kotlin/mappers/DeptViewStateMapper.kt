package mappers

import biz.proc.ContextState
import biz.proc.DeptCommand
import biz.proc.DeptContext
import models.DeptViewState

fun DeptContext.toDeptViewState() = DeptViewState(
	depts = depts,
	authId = authId,
	currentDeptId = currentDeptId,
	parentId = parentDeptId,
	onStart = onStart,
	success = state == ContextState.FINISHING,
	errors = errors.map { it.message }
)

fun DeptViewState.toDeptContext(command: DeptCommand) = DeptContext(
	command = command,
	depts = depts,
	authId = authId,
	currentDeptId = currentDeptId,
	parentDeptId = parentId,
	onStart = onStart,
)

