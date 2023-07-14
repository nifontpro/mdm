package vm

import biz.proc.EventCommand
import biz.proc.EventProcessor
import com.adeo.kviewmodel.BaseSharedViewModel
import di.Inject
import kotlinx.coroutines.launch
import mappers.toEventContext
import mappers.toEventViewState
import model.EventAction
import model.EventEvent
import model.EventViewState

class EventViewModel : BaseSharedViewModel<EventViewState, EventAction, EventEvent>(
	initialState = EventViewState()
) {

	override fun obtainEvent(viewEvent: EventEvent) {
		viewModelScope.launch {
			when (viewEvent) {
				EventEvent.OnResume -> getSettings()
				EventEvent.Clear -> viewAction = null
				is EventEvent.ClickEvent -> obtainEventClick(eventId = viewEvent.eventId)
				EventEvent.OnLoadNextPage -> obtainLoadNextPage()
			}
		}
	}

	private fun obtainEventClick(eventId: Long) {
//		viewState = process(
//			command = DeptCommand.TO_DEPT,
//			viewState = viewState,
//			ignoreSuccess = true,
//			clickDeptId = deptId
//		)
	}

	private suspend fun getSettings() {
		viewState = process(command = EventCommand.GET_SETTINGS, viewState = viewState)
	}

	private suspend fun obtainLoadNextPage() {
		viewState = viewState.copy(isLoading = true)
		viewState = process(command = EventCommand.GET_EVENTS_NEXT_PAGE, viewState = viewState)
	}

}

suspend fun process(
	command: EventCommand,
	viewState: EventViewState,
	ignoreSuccess: Boolean = false,
	clickEventId: Long = 0,
): EventViewState {
	val userProcessor: EventProcessor = Inject.instance()
	val context = viewState.toEventContext(command = command, clickEventId = clickEventId)
	userProcessor.exec(context)
	return context.toEventViewState(ignoreSuccess)
}