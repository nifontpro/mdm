package biz.proc

import di.Inject
import model.BaseEvent
import model.response.PageInfo
import repo.EventRepository

class EventContext(
	override var command: IBaseCommand,
	override var authId: Long = 0,
	override var deptId: Long = 0,
	override var onStart: Boolean = true,

	var events: List<BaseEvent> = emptyList(),

//	var clickEventId: Long = 0,
	override var isLoading: Boolean = false,
	override var pageInfo: PageInfo = PageInfo(),

	) : BaseContext() {
	val eventRepository: EventRepository = Inject.instance()

	companion object {
		const val REPO = "Event"
	}

}

enum class EventCommand : IBaseCommand {
	GET_SETTINGS,
	GET_EVENTS_NEXT_PAGE
}
