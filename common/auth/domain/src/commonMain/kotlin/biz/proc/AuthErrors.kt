package biz.proc

import biz.helper.fail
import biz.helper.restError
import biz.proc.AuthContext.Companion.REPO

fun AuthContext.getAuthError() {
	fail(
		restError(
			repository = REPO,
			violationCode = "get",
			description = "Ошибка чтения данных авторизации"
		)
	)
}