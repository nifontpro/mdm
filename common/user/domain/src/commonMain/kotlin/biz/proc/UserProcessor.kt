package biz.proc

import biz.workers.*
import ru.md.base_domain.biz.proc.IBaseProcessor
import ru.md.cor.rootChain

class UserProcessor : IBaseProcessor<UserContext> {

	override suspend fun exec(ctx: UserContext) = businessChain.exec(ctx)

	companion object {

		private val businessChain = rootChain {
			initStatus()

			operation("Чтение настроек", UserCommand.GET_SETTINGS) {
				getUserCurrentSettings("Получаем начальные настройки")
				getUserCurrentSettingsOnStart("Получаем начальные настройки")
//				getUserList("Получаем список сотрудников отдела")
				getUsersNextPage("Получаем следующую страницу сотрудников")
			}

			operation("Чтение настроек", UserCommand.GET_USERS_NEXT_PAGE) {
				getUsersNextPage("Получаем следующую страницу сотрудников")
			}

			finishOperation()
		}.build()
	}
}