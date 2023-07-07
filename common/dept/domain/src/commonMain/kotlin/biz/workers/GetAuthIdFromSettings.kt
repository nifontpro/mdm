package biz.workers

import biz.helper.errorValidation
import biz.helper.fail
import biz.proc.ContextState
import biz.proc.DeptContext
import biz.proc.DeptContext.Companion.REPO
import ru.md.cor.ICorChainDsl
import ru.md.cor.worker
import logger.KLog

fun ICorChainDsl<DeptContext>.getAuthIdFromSettings(title: String) = worker {
	this.title = title
	on { state == ContextState.RUNNING }

	handle {

		val newAuthId = authSettings.getAuthId()
		KLog.i(REPO, "authId = $authId, newAuthId = $newAuthId")
		if (newAuthId == 0L) {

			fail(
				errorValidation(
					field = "authId",
					violationCode = "empty",
					description = "Необходимо авторизоваться или выбрать профиль"
				)
			)
		}

		if (newAuthId == authId && state == ContextState.RUNNING) {
			state = ContextState.FINISHING
			return@handle
		}

		authId = newAuthId

	}
}
