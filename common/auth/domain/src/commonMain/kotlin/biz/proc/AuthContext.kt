package biz.proc

import di.Inject
import repo.AuthRepository

class AuthContext(
	override var command: IBaseCommand,
	override var onStart: Boolean = true,
	override var isLoading: Boolean = false,

	var isAuth: Boolean = false,
) : BaseContext() {

	val authRepository: AuthRepository = Inject.instance()
//	val authSettings: AuthSettings = Inject.instance()

	companion object {
		const val REPO = "Auth"
	}

}

enum class AuthCommand : IBaseCommand {
	GET_AUTH_STATE,
}
