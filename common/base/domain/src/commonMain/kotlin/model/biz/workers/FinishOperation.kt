package model.biz.workers

import model.biz.proc.BaseContext
import model.biz.proc.ContextState
import ru.md.cor.ICorChainDsl
import ru.md.cor.worker

fun <T : BaseContext> ICorChainDsl<T>.finishOperation(
	title: String = "Завершение операции"
) = worker {
	this.title = title
	on { state == ContextState.RUNNING }
	handle {
		state = ContextState.FINISHING
	}
}
