package biz.workers

import biz.helper.checkResponse
import biz.proc.ContextState
import biz.proc.DeptContext
import biz.proc.DeptContext.Companion.REPO
import biz.proc.getDeptError
import logger.KLog
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
					parentId = parentDeptId
				)
			)
		} ?: return@handle

	}

	except {
		KLog.e(REPO, it.message.toString())
		getDeptError()
	}
}
