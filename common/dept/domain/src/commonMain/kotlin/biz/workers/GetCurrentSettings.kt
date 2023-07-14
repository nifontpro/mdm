package biz.workers

import biz.helper.checkResponse
import biz.proc.AuthIdEmptyError
import biz.proc.ContextState
import biz.proc.DeptContext
import model.request.GetAuthDeptRequest
import ru.md.cor.ICorChainDsl
import ru.md.cor.worker

fun ICorChainDsl<DeptContext>.getDeptCurrentSettings(title: String) = worker {
	this.title = title
	on { !onStart && state == ContextState.RUNNING}

	handle {

		val newAuthId = currentSettings.getAuthId()
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
