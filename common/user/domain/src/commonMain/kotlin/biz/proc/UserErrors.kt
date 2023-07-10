package biz.proc

import biz.helper.errorValidation
import biz.helper.fail
import biz.helper.restError
import biz.proc.UserContext.Companion.REPO

fun UserContext.getUserError() {
	fail(
		restError(
			repository = REPO,
			violationCode = "get",
			description = "Ошибка чтения данных сотрудников"
		)
	)
}

fun UserContext.DeptIdEmptyError() {
	fail(
		errorValidation(
			field = "deptId",
			violationCode = "empty",
			description = "Отдел не выбран"
		)
	)
}