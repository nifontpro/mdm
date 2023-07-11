package biz.proc

import auth.repo.AuthSettings
import curent.repo.CurrentSettings
import di.Inject
import model.User
import model.response.PageInfo
import repo.UserRepository

class UserContext(
	override var command: IBaseCommand,
	override var authId: Long = 0,
	override var pageInfo: PageInfo = PageInfo(),
	var deptId: Long = 0,
//	var user: User = User(),
	var users: List<User> = emptyList(),

	var onStart: Boolean = true,
	var clickUserId: Long = 0,
	var isLoading: Boolean = false,
) : BaseContext() {
	val userRepository: UserRepository = Inject.instance()
	val authSettings: AuthSettings = Inject.instance()
	val currentSettings: CurrentSettings = Inject.instance()

	companion object {
		const val REPO = "User"
	}

}

enum class UserCommand : IBaseCommand {
	GET_SETTINGS,
	GET_USERS_NEXT_PAGE
}
