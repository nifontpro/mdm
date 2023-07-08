package biz.proc

import biz.helper.errorValidation
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