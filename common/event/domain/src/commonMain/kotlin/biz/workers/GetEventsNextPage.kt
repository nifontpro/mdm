package biz.workers

import biz.helper.checkResponse
import biz.proc.ContextState
import biz.proc.EventContext
import biz.proc.EventContext.Companion.REPO
import biz.proc.getEventError
import constants.PAGE_SIZE
import logger.KLog
import model.request.BaseRequest
import model.request.GetAllEventsRequest
import ru.md.cor.ICorChainDsl
import ru.md.cor.worker

fun ICorChainDsl<EventContext>.getEventsNextPage(title: String) = worker {
	this.title = title
	on { state == ContextState.RUNNING }

	handle {

		val newEvents = checkResponse {
			eventRepository.getEvents(
				request = GetAllEventsRequest(
					authId = authId,
					deptId = deptId,
					baseRequest = BaseRequest(
						page = pageInfo.pageNumber,
						pageSize = PAGE_SIZE
					)
				)
			)
		} ?: return@handle

		events = events + newEvents

		pageInfo = pageInfo.copy(pageNumber = pageInfo.pageNumber + 1)
		isLoading = false
	}

	except {
		KLog.e(REPO, it.message.toString())
		getEventError()
	}
}
