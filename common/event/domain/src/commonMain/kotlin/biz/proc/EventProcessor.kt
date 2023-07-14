package biz.proc

import biz.workers.checkClearEvents
import biz.workers.getCurrentAuthIdAndDeptId
import biz.workers.getCurrentAuthIdAndDeptIdOnStart
import biz.workers.getEventsNextPage
import biz.workers.operation.finishOperation
import biz.workers.operation.initStatus
import biz.workers.operation.operation
import ru.md.base_domain.biz.proc.IBaseProcessor
import ru.md.cor.rootChain

class EventProcessor : IBaseProcessor<EventContext> {

	override suspend fun exec(ctx: EventContext) = businessChain.exec(ctx)

	companion object {

		private val businessChain = rootChain {
			initStatus()

			operation("Чтение настроек", EventCommand.GET_SETTINGS) {
				getCurrentAuthIdAndDeptId("Получаем начальные настройки")
				getCurrentAuthIdAndDeptIdOnStart("Получаем начальные настройки")
				checkClearEvents("Очищаем список по требованию")
				getEventsNextPage("Получаем следующую страницу событий")
			}

			operation("Получение следующей страницы", EventCommand.GET_EVENTS_NEXT_PAGE) {
				getEventsNextPage("Получаем следующую страницу событий")
			}

			finishOperation()
		}.build()
	}
}