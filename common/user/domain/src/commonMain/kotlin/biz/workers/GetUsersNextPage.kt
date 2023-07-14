package biz.workers

import biz.helper.checkResponse
import biz.proc.ContextState
import biz.proc.UserContext
import biz.proc.UserContext.Companion.REPO
import biz.proc.getUserError
import constants.PAGE_SIZE
import logger.KLog
import model.request.BaseRequest
import model.request.GetUsersByDeptRequest
import ru.md.cor.ICorChainDsl
import ru.md.cor.worker

fun ICorChainDsl<UserContext>.getUsersNextPage(title: String) = worker {
	this.title = title
	on { state == ContextState.RUNNING }

	handle {

		KLog.i("User", "Get next page: ${pageInfo.pageNumber}")

		val newUsers = checkResponse {
			userRepository.getByDept(
				request = GetUsersByDeptRequest(
					authId = authId,
					deptId = deptId,
					baseRequest = BaseRequest(
						page = pageInfo.pageNumber,
						pageSize = PAGE_SIZE
					)
				)
			)
		} ?: return@handle

		users = users + newUsers

		pageInfo = pageInfo.copy(pageNumber = pageInfo.pageNumber + 1)
		isLoading = false
		KLog.d(REPO, pageInfo.toString())
	}

	except {
		KLog.e(REPO, it.message.toString())
		getUserError()
	}
}
