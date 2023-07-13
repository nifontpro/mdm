package biz.proc

import biz.helper.errorValidation
import biz.helper.fail
import biz.helper.restError

fun BaseContext.saveSettingsError() {
	fail(
		restError(
			repository = "Settings",
			violationCode = "save",
			description = "Ошибка сохранения настроек в локальное хранилище"
		)
	)
}

fun BaseContext.AuthIdEmptyError() {
	fail(
		errorValidation(
			field = "authId",
			violationCode = "empty",
			description = "Необходимо авторизоваться или выбрать профиль"
		)
	)
}

fun BaseContext.DeptIdEmptyError() {
	fail(
		errorValidation(
			field = "deptId",
			violationCode = "empty",
			description = "Отдел не выбран"
		)
	)
}