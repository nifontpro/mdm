package biz.workers

import biz.proc.*
import logger.KLog
import ru.md.cor.ICorChainDsl
import ru.md.cor.worker

fun <T : BaseContext> ICorChainDsl<T>.getCurrentAuthIdAndDeptIdOnStart(title: String) = worker {
	this.title = title
	on { onStart && state == ContextState.RUNNING }

	handle {

		onStart = false

		authId = currentSettings.getAuthId()
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
