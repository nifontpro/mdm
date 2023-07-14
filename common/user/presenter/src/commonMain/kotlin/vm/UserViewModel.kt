package vm

import biz.proc.UserCommand
import biz.proc.UserProcessor
import com.adeo.kviewmodel.BaseSharedViewModel
import di.Inject
import kotlinx.coroutines.launch
import logger.KLog
import mappers.toUserContext
import mappers.toUserViewState
import model.UserAction
import model.UserEvent
import model.UserViewState

class UserViewModel : BaseSharedViewModel<UserViewState, UserAction, UserEvent>(
	initialState = UserViewState()
) {

	override fun obtainEvent(viewEvent: UserEvent) {
		viewModelScope.launch {
			when (viewEvent) {
				UserEvent.OnResume -> getSettings()
				is UserEvent.OnTest -> onTest(message = viewEvent.message)
				UserEvent.Clear -> viewAction = null
				is UserEvent.ClickUser -> obtainUserClick(deptId = viewEvent.deptId)
				UserEvent.OnLoadNextPage -> obtainLoadNextPage()
			}
		}
	}

	private fun obtainUserClick(deptId: Long) {
//		viewState = process(
//			command = DeptCommand.TO_DEPT,
//			viewState = viewState,
//			ignoreSuccess = true,
//			clickDeptId = deptId
//		)
	}

	private suspend fun getSettings() {
		viewState = process(command = UserCommand.GET_SETTINGS, viewState = viewState)
	}

	private suspend fun obtainLoadNextPage() {
		KLog.d("User", "Is Next page Loading...")
		viewState = viewState.copy(isLoading = true)
		viewState = process(command = UserCommand.GET_USERS_NEXT_PAGE, viewState = viewState)
	}

	private fun onTest(message: String) {
		viewAction = UserAction.Test(message)
	}

}

suspend fun process(
	command: UserCommand,
	viewState: UserViewState,
	ignoreSuccess: Boolean = false,
	clickUserId: Long = 0,
): UserViewState {
	val userProcessor: UserProcessor = Inject.instance()
	val context = viewState.toUserContext(command = command, clickUserId = clickUserId)
	userProcessor.exec(context)
	return context.toUserViewState(ignoreSuccess)
}