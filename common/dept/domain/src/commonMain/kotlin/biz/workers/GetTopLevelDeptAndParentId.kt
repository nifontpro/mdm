package biz.workers

import biz.helper.checkResponse
import biz.proc.ContextState
import biz.proc.DeptContext
import biz.proc.DeptContext.Companion.REPO
import biz.proc.getDeptError
import logger.KLog
import model.request.GetDeptByIdRequest
import ru.md.cor.ICorChainDsl
import ru.md.cor.worker

fun ICorChainDsl<DeptContext>.getTopLevelDeptAndParentId(title: String) = worker {
	this.title = title
	on { state == ContextState.RUNNING }

	handle {

		dept = checkResponse {
			deptRepository.getDeptById(
				request = GetDeptByIdRequest(
					authId = authId,
					deptId = parentDeptId
				)
			)
		} ?: return@handle

		parentDeptId = dept.parentId
		currentSettings.saveParentDeptId(parentDeptId)

	}

	except {
		KLog.e(REPO, it.message.toString())
		getDeptError()
	}
}
