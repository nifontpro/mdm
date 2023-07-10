package vm

import biz.proc.UserCommand
import biz.proc.UserProcessor
import com.adeo.kviewmodel.BaseSharedViewModel
import di.Inject
import kotlinx.coroutines.launch
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
				UserEvent.OnTopLevel -> onTopLevel()
				is UserEvent.OnTest -> onTest(message = viewEvent.message)
				UserEvent.Clear -> viewAction = null
				is UserEvent.ClickUser -> obtainUserClick(deptId = viewEvent.deptId)
			}
		}
	}

	private suspend fun obtainUserClick(deptId: Long) {
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

	private suspend fun onTopLevel() {
//		viewState = process(
//			command = DeptCommand.ON_TOP_LEVEL,
//			viewState = viewState,
//			ignoreSuccess = true
//		)

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