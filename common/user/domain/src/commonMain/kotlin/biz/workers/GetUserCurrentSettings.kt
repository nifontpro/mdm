package biz.workers

import biz.proc.AuthIdEmptyError
import biz.proc.ContextState
import biz.proc.DeptIdEmptyError
import biz.proc.UserContext
import logger.KLog
import ru.md.cor.ICorChainDsl
import ru.md.cor.worker

fun ICorChainDsl<UserContext>.getUserCurrentSettings(title: String) = worker {
	this.title = title
	on { !onStart && state == ContextState.RUNNING}

	handle {

		val newDeptId = currentSettings.getCurrentDeptId()
		KLog.i("User", "On Resume newDeptId=$newDeptId")
		if (newDeptId == 0L) {
			DeptIdEmptyError()
			return@handle
		}

		// Если смены отдела не произошло, то завершаем операцию
		if (newDeptId == deptId) {
			state = ContextState.FINISHING
			return@handle
		}

		deptId = newDeptId

		authId = authSettings.getAuthId()
		if (authId == 0L) {
			AuthIdEmptyError()
			return@handle
		}


	}
}
