package biz.workers

import biz.helper.checkResponse
import biz.proc.ContextState
import biz.proc.DeptContext
import model.request.GetAuthDeptRequest
import ru.md.cor.ICorChainDsl
import ru.md.cor.worker

fun ICorChainDsl<DeptContext>.getAuthDept(title: String) = worker {
	this.title = title
	on { state == ContextState.RUNNING }

	handle {

		dept = checkResponse {
			deptRepository.getAuthDept(request = GetAuthDeptRequest(authId = authId))
		} ?: return@handle

		selectDeptId = dept.id

	}
}
