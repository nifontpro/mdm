package model.biz.workers

import model.biz.proc.BaseContext
import model.biz.proc.ContextState
import ru.md.cor.ICorChainDsl
import ru.md.cor.worker

fun <T : BaseContext> ICorChainDsl<T>.initStatus(
	title: String = "Инициализация статуса"
) = worker {
	this.title = title
	on { state == ContextState.NONE }
	handle { state = ContextState.STARTING }
}
