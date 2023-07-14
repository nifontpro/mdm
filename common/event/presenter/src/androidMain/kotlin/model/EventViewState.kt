package model

import biz.proc.BaseState
import model.response.PageInfo

data class EventViewState(
	override val authId: Long = 0,
	override val onStart: Boolean = true,
	override val isLoading: Boolean = false,
	override val pageInfo: PageInfo = PageInfo(),

	val success: Boolean = true,
	val errors: List<String> = emptyList(),

	val deptId: Long = 0,
	val events: List<BaseEvent> = emptyList(),
) : BaseState