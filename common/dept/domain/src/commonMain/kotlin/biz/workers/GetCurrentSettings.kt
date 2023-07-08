package biz.workers

import biz.helper.checkResponse
import biz.proc.AuthIdEmptyError
import biz.proc.ContextState
import biz.proc.DeptContext
import biz.proc.DeptContext.Companion.REPO
import logger.KLog
import model.request.GetAuthDeptRequest
import ru.md.cor.ICorChainDsl
import ru.md.cor.worker

fun ICorChainDsl<DeptContext>.getCurrentSettings(title: String) = worker {
	this.title = title
	on { !onStart && state == ContextState.RUNNING && !onStart }

	handle {

		val newAuthId = authSettings.getAuthId()
		KLog.d(REPO, "authId = $authId, newAuthId = $newAuthId")

		if (newAuthId == 0L) {
			AuthIdEmptyError()
			return@handle
		}

		// Если смены профиля не произошло, то завершаем операцию
		if (newAuthId == authId) {
			state = ContextState.FINISHING
			return@handle
		}

		authId = newAuthId

		dept = checkResponse {
			deptRepository.getAuthDept(request = GetAuthDeptRequest(authId = authId))
		} ?: return@handle

		currentDeptId = dept.id
		parentDeptId = dept.parentId
		currentSettings.saveCurrentDeptId(currentDeptId)
		currentSettings.saveParentDeptId(parentDeptId)

	}
}
