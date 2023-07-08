package vm

import biz.proc.DeptCommand
import biz.proc.DeptProcessor
import com.adeo.kviewmodel.BaseSharedViewModel
import di.Inject
import kotlinx.coroutines.launch
import mappers.toDeptContext
import mappers.toDeptViewState
import models.DeptAction
import models.DeptEvent
import models.DeptViewState

class DeptViewModel : BaseSharedViewModel<DeptViewState, DeptAction, DeptEvent>(
	initialState = DeptViewState()
) {

	override fun obtainEvent(viewEvent: DeptEvent) {
		viewModelScope.launch {
			when (viewEvent) {
				DeptEvent.OnResume -> getSettings()
				DeptEvent.OnTopLevel -> onTopLevel()
				is DeptEvent.CurrentDeptIdChanged -> obtainCurrentDeptIdChanged(currentDeptId = viewEvent.currentDeptId)
				is DeptEvent.OnTest -> onTest(message = viewEvent.message)
				DeptEvent.Clear -> viewAction = null
			}
		}
	}

	private suspend fun getSettings() {
		viewState = process(command = DeptCommand.GET_SETTINGS, viewState = viewState)
	}

	private suspend fun onTopLevel() {
		viewState = process(
			command = DeptCommand.ON_TOP_LEVEL,
			viewState = viewState,
			ignoreSuccess = true
		)

	}

	private fun onTest(message: String) {
		viewAction = DeptAction.Test(message)
	}

	private suspend fun obtainCurrentDeptIdChanged(currentDeptId: Long) {
		viewState = process(
			command = DeptCommand.CHANGE_CURRENT_DEPT,
			viewState = viewState.copy(currentDeptId = currentDeptId)
		)
	}

}

suspend fun process(
	command: DeptCommand,
	viewState: DeptViewState,
	ignoreSuccess: Boolean = false
): DeptViewState {
	val deptProcessor: DeptProcessor = Inject.instance()
	val context = viewState.toDeptContext(command = command)
	deptProcessor.exec(context)
	return context.toDeptViewState(ignoreSuccess)
}