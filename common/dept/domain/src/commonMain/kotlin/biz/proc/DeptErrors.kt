package biz.proc

import biz.helper.fail
import biz.helper.restError
import biz.proc.DeptContext.Companion.REPO

fun DeptContext.getDeptError() {
	fail(
		restError(
			repository = REPO,
			violationCode = "get",
			description = "Ошибка чтения отделов"
		)
	)
}