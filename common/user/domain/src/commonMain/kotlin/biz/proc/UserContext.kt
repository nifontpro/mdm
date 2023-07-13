package biz.proc

import di.Inject
import model.User
import model.response.PageInfo
import repo.UserRepository

class UserContext(
	override var command: IBaseCommand,
	override var authId: Long = 0,
	override var pageInfo: PageInfo = PageInfo(),
	override var deptId: Long = 0,
	override var onStart: Boolean = true,

	var users: List<User> = emptyList(),

//	var clickUserId: Long = 0,
	override var isLoading: Boolean = false,
) : BaseContext() {
	val userRepository: UserRepository = Inject.instance()

	companion object {
		const val REPO = "User"
	}

}

enum class UserCommand : IBaseCommand {
	GET_SETTINGS,
	GET_USERS_NEXT_PAGE
}
