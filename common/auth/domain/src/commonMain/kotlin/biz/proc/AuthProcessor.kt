package biz.proc

import biz.workers.getAuthState
import biz.workers.operation.finishOperation
import biz.workers.operation.initStatus
import biz.workers.operation.operation
import ru.md.base_domain.biz.proc.IBaseProcessor
import ru.md.cor.rootChain
import ru.md.cor.worker

class AuthProcessor : IBaseProcessor<AuthContext> {

	override suspend fun exec(ctx: AuthContext) = businessChain.exec(ctx)

	companion object {

		private val businessChain = rootChain<AuthContext> {
			initStatus()

			operation("Проверка авторизации", AuthCommand.GET_AUTH_STATE) {
				getAuthState("Проверяем состояние авторизации пользователя")
				worker("") { isLoading = false }
			}

			finishOperation()
		}.build()
	}
}