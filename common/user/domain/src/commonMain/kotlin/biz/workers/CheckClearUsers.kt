package biz.workers

import biz.proc.ContextState
import biz.proc.UserContext
import ru.md.cor.ICorChainDsl
import ru.md.cor.worker

fun ICorChainDsl<UserContext>.checkClearUsers(title: String) = worker {
	this.title = title
	on { clearData && state == ContextState.RUNNING }

	handle {
		users = emptyList()
	}
}
