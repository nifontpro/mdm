package biz.proc

import biz.helper.fail
import biz.helper.restError
import biz.proc.EventContext.Companion.REPO

fun EventContext.getEventError() {
	fail(
		restError(
			repository = REPO,
			violationCode = "get",
			description = "Ошибка получения событий"
		)
	)
}