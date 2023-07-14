package mappers

import biz.proc.ContextState
import biz.proc.UserCommand
import biz.proc.UserContext
import model.UserViewState

fun UserContext.toUserViewState(ignoreSuccess: Boolean = false) = UserViewState(
	users = users,
	authId = authId,
	deptId = deptId,
	onStart = onStart,
	success = if (ignoreSuccess) true else state == ContextState.FINISHING,
	errors = errors.map { it.message },
	pageInfo = pageInfo,
	isLoading = isLoading,
)

fun UserViewState.toUserContext(
	command: UserCommand,
	clickUserId: Long
) = UserContext(
	command = command,
	users = users,
	authId = authId,
	deptId = deptId,
	onStart = onStart,
	pageInfo = pageInfo,
	isLoading = isLoading
)

