package mappers

import biz.proc.ContextState
import biz.proc.EventCommand
import biz.proc.EventContext
import model.EventViewState

fun EventContext.toEventViewState(ignoreSuccess: Boolean = false) = EventViewState(
	events = events,
	authId = authId,
	deptId = deptId,
	onStart = onStart,
	success = if (ignoreSuccess) true else state == ContextState.FINISHING,
	errors = errors.map { it.message },
	pageInfo = pageInfo,
	isLoading = isLoading,
)

fun EventViewState.toEventContext(
	command: EventCommand,
	clickEventId: Long
) = EventContext(
	command = command,
	events = events,
	authId = authId,
	deptId = deptId,
	onStart = onStart,
	pageInfo = pageInfo,
	isLoading = isLoading
)

