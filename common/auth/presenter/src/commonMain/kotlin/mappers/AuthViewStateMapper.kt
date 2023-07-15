package mappers

import biz.proc.AuthCommand
import biz.proc.AuthContext
import models.AuthViewState

fun AuthViewState.toAuthContext(
	command: AuthCommand,
) = AuthContext(
	command = command,
	isLoading = isLoading,
	isAuth = isAuth
)


fun AuthContext.toAuthViewState() = AuthViewState(
	isLoading = isLoading,
	isAuth = isAuth,
)