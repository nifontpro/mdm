package biz.workers

import biz.helper.checkResponse
import biz.proc.ContextState
import biz.proc.UserContext
import biz.proc.UserContext.Companion.REPO
import biz.proc.getUserError
import logger.KLog
import model.request.BaseRequest
import model.request.GetUsersByDeptRequest
import ru.md.cor.ICorChainDsl
import ru.md.cor.worker

fun ICorChainDsl<UserContext>.getUserList(title: String) = worker {
	this.title = title
	on { state == ContextState.RUNNING }

	handle {

		KLog.i("User", "authId=$authId, deptId=$deptId")

		users = checkResponse {
			userRepository.getByDept(
				request = GetUsersByDeptRequest(
					authId = authId,
					deptId = deptId,
					baseRequest = BaseRequest(

					)
				)
			)
		} ?: return@handle

	}

	except {
		KLog.e(REPO, it.message.toString())
		getUserError()
	}
}
