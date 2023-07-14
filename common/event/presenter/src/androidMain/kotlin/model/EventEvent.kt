package model

sealed class EventEvent {
	object OnResume : EventEvent()
	object OnLoadNextPage : EventEvent()
	object Clear : EventEvent()
	data class ClickEvent(val eventId: Long) : EventEvent()
}