package biz.proc

import biz.workers.*
import ru.md.base_domain.biz.proc.IBaseProcessor
import ru.md.cor.rootChain

class DeptProcessor : IBaseProcessor<DeptContext> {

	override suspend fun exec(ctx: DeptContext) = businessChain.exec(ctx)

	companion object {

		private val businessChain = rootChain {
			initStatus()

			operation("Чтение настроек", DeptCommand.GET_SETTINGS) {
				getAuthIdFromSettings("Получаем authId")
				getAuthDept("Получаем отдел авторизованного пользователя")
				getDeptList("Получаем список отделов")
			}

			finishOperation()
		}.build()
	}
}