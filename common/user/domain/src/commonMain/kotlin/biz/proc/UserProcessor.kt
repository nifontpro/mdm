package biz.proc

import biz.workers.*
import biz.workers.operation.finishOperation
import biz.workers.operation.initStatus
import biz.workers.operation.operation
import ru.md.base_domain.biz.proc.IBaseProcessor
import ru.md.cor.rootChain

class UserProcessor : IBaseProcessor<UserContext> {

	override suspend fun exec(ctx: UserContext) = businessChain.exec(ctx)

	companion object {

		private val businessChain = rootChain {
			initStatus()

			operation("Чтение настроек", UserCommand.GET_SETTINGS) {
				getCurrentAuthIdAndDeptId("Получаем начальные настройки")
				getCurrentAuthIdAndDeptIdOnStart("Получаем начальные настройки")
				checkClearUsers("Очищаем список по требованию")
				getUsersNextPage("Получаем следующую страницу сотрудников")
			}

			operation("Чтение настроек", UserCommand.GET_USERS_NEXT_PAGE) {
				getUsersNextPage("Получаем следующую страницу сотрудников")
			}

			finishOperation()
		}.build()
	}
}