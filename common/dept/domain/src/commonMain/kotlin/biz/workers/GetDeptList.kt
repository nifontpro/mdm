package biz.workers

import biz.helper.checkResponse
import biz.proc.ContextState
import biz.proc.DeptContext
import model.request.GetCurrentDeptsRequest
import ru.md.cor.ICorChainDsl
import ru.md.cor.worker

fun ICorChainDsl<DeptContext>.getDeptList(title: String) = worker {
	this.title = title
	on { state == ContextState.RUNNING }

	handle {

		depts = checkResponse {
			deptRepository.getCurrentDepts(
				request = GetCurrentDeptsRequest(
					authId = authId,
					parentId = dept.parentId
				)
			)
		} ?: return@handle

	}
}
