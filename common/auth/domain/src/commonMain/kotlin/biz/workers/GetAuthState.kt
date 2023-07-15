package biz.workers

import biz.helper.checkResponse
import biz.proc.AuthContext
import biz.proc.AuthContext.Companion.REPO
import biz.proc.ContextState
import biz.proc.getAuthError
import logger.KLog
import ru.md.cor.ICorChainDsl
import ru.md.cor.worker

fun ICorChainDsl<AuthContext>.getAuthState(title: String) = worker {
	this.title = title
	on { state == ContextState.RUNNING }

	handle {
		val res = checkResponse { authRepository.checkAuth() } ?: return@handle
		KLog.d("Auth", "res: $res")
		isAuth = true
	}

	except {
		KLog.e(REPO, it.message.toString())
		isAuth = false
		getAuthError()
	}
}
