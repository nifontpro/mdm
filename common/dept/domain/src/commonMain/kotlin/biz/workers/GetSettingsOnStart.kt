package biz.workers

import biz.helper.checkResponse
import biz.proc.AuthIdEmptyError
import biz.proc.ContextState
import biz.proc.DeptContext
import biz.proc.getDeptError
import model.request.GetAuthDeptRequest
import ru.md.cor.ICorChainDsl
import ru.md.cor.worker

fun ICorChainDsl<DeptContext>.getSettingsOnStart(title: String) = worker {
	this.title = title
	on { onStart && state == ContextState.RUNNING }

	handle {

		onStart = false
		authId = authSettings.getAuthId()

		if (authId == 0L) {
			AuthIdEmptyError()
			return@handle
		}

		currentDeptId = currentSettings.getCurrentDeptId()
		parentDeptId = currentSettings.getParentDeptId()

		if (currentDeptId == 0L || parentDeptId == 0L) {
			dept = checkResponse {
				deptRepository.getAuthDept(request = GetAuthDeptRequest(authId = authId))
			} ?: return@handle

			currentDeptId = dept.id
			parentDeptId = dept.parentId
			currentSettings.saveCurrentDeptId(currentDeptId)
			currentSettings.saveParentDeptId(parentDeptId)
		}
	}

	except {
		getDeptError()
	}
}
