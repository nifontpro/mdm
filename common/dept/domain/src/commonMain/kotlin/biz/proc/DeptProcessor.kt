package biz.proc

import biz.workers.*
import biz.workers.operation.finishOperation
import biz.workers.operation.initStatus
import biz.workers.operation.operation
import ru.md.base_domain.biz.proc.IBaseProcessor
import ru.md.cor.rootChain

class DeptProcessor : IBaseProcessor<DeptContext> {

	override suspend fun exec(ctx: DeptContext) = businessChain.exec(ctx)

	companion object {

		private val businessChain = rootChain {
			initStatus()

			operation("Чтение настроек", DeptCommand.GET_SETTINGS) {
				getDeptCurrentSettings("Получаем начальные настройки")
				getSettingsOnStart("Получаем начальные настройки при старте")
				getDeptList("Получаем список отделов")
			}

			operation("Сохранение в локальное хранилище выбранного отдела", DeptCommand.CHANGE_CURRENT_DEPT) {
				saveCurrentDeptId("Сохраняем текущий отдел")
			}

			operation("Переход на уровень вверх", DeptCommand.ON_TOP_LEVEL) {
				getTopLevelDeptAndParentId("Получаем верхний отдел и parentId")
				getDeptList("Получаем список отделов")
			}

			operation("Переход на отдел", DeptCommand.TO_DEPT) {
				getDeptListForClick("Получаем список отделов при нажатии")
				saveParentDeptId("Сохраняем родительский отдел")
			}

			finishOperation()
		}.build()
	}
}