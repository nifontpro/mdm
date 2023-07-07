package mappers

import biz.proc.ContextState
import biz.proc.DeptCommand
import biz.proc.DeptContext
import models.DeptViewState

fun DeptContext.toDeptViewState() = DeptViewState(
	depts = depts,
	authId = authId,
	selectDeptId = selectDeptId,
	parentId = parentId,
	success = state == ContextState.FINISHING,
	errors = errors.map { it.message }
)

fun DeptViewState.toDeptContext(command: DeptCommand) = DeptContext(
	command = command,
	depts = depts,
	authId = authId,
	selectDeptId = selectDeptId,
	parentId = parentId
)

