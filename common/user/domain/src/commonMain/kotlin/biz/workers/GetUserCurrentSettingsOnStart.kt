package biz.workers

import biz.proc.AuthIdEmptyError
import biz.proc.ContextState
import biz.proc.DeptIdEmptyError
import biz.proc.UserContext
import logger.KLog
import ru.md.cor.ICorChainDsl
import ru.md.cor.worker

fun ICorChainDsl<UserContext>.getUserCurrentSettingsOnStart(title: String) = worker {
	this.title = title
	on { onStart && state == ContextState.RUNNING}

	handle {

		onStart = false

		authId = authSettings.getAuthId()
		if (authId == 0L) {
			AuthIdEmptyError()
			return@handle
		}

		deptId = currentSettings.getCurrentDeptId()
		if (deptId == 0L) {
			DeptIdEmptyError()
			return@handle
		}

		KLog.i("User", "On start authId=$authId, deptId=$deptId")
	}
}
