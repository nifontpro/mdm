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

//	init {
//		getSettings()
//	}

	override fun obtainEvent(viewEvent: DeptEvent) {
		when (viewEvent) {
			DeptEvent.OnResume -> onResume()
			is DeptEvent.CurrentDeptIdChanged -> obtainCurrentDeptIdChanged(currentDeptId = viewEvent.currentDeptId)
		}
	}

	private fun getSettings() {
		viewModelScope.launch {
			viewState = process(command = DeptCommand.GET_SETTINGS, viewState = viewState)
		}
	}

	private fun obtainCurrentDeptIdChanged(currentDeptId: Long) {
		viewModelScope.launch {
			viewState = process(
				command = DeptCommand.CHANGE_CURRENT_DEPT,
				viewState = viewState.copy(currentDeptId = currentDeptId)
			)
		}
	}

	private fun onResume() {
		getSettings()
	}

}

suspend fun process(
	command: DeptCommand,
	viewState: DeptViewState
): DeptViewState {
	val deptProcessor: DeptProcessor = Inject.instance()
	val context = viewState.toDeptContext(command = command)
	deptProcessor.exec(context)
	return context.toDeptViewState()
}