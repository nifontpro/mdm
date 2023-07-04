package biz

import model.Dept
import model.biz.workers.finishOperation
import model.biz.workers.initStatus
import model.biz.workers.operation
import ru.md.base_domain.biz.proc.IBaseProcessor
import ru.md.cor.rootChain
import ru.md.cor.worker

class DeptProcessor : IBaseProcessor<DeptContext> {

	override suspend fun exec(ctx: DeptContext) = businessChain.exec(ctx)

	companion object {

		private val businessChain = rootChain<DeptContext> {
			initStatus()

			operation("Тест", DeptCommand.TEST) {
				worker("Test") {
					depts = depts + Dept(id = 777, name = "Test dept")
				}
			}

			finishOperation()
		}.build()
	}
}