package biz.proc

import biz.workers.*
import model.Dept
import ru.md.base_domain.biz.proc.IBaseProcessor
import ru.md.cor.rootChain
import ru.md.cor.worker

class DeptProcessor : IBaseProcessor<DeptContext> {

	override suspend fun exec(ctx: DeptContext) = businessChain.exec(ctx)

	companion object {

		private val businessChain = rootChain<DeptContext> {
			initStatus()

			operation("Чтение настроек", DeptCommand.GET_SETTINGS) {
				getAuthIdFromSettings("Получаем authId")
				getAuthDept("Получаем отдел авторизованного пользователя")
				getDeptList("Получаем список отделов")
			}

			operation("Тест", DeptCommand.TEST) {
				worker("Test") {
					depts = depts + Dept(id = 777, name = "Test dept")
				}
			}

			finishOperation()
		}.build()
	}
}