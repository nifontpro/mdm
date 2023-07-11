package model

sealed class UserEvent {
	object OnResume : UserEvent()
	object OnLoadNextPage : UserEvent()
	data class OnTest(val message: String) : UserEvent()
	object Clear : UserEvent()
	data class ClickUser(val deptId: Long) : UserEvent()
}