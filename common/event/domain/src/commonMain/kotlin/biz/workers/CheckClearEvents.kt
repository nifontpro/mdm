package biz.workers

import biz.proc.ContextState
import biz.proc.EventContext
import ru.md.cor.ICorChainDsl
import ru.md.cor.worker

fun ICorChainDsl<EventContext>.checkClearEvents(title: String) = worker {
	this.title = title
	on { clearData && state == ContextState.RUNNING }

	handle {
		events = emptyList()
	}
}
