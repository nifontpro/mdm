package models

sealed class DeptEvent {
	object OnResume : DeptEvent()
	object OnTopLevel : DeptEvent()
	data class CurrentDeptIdChanged(val currentDeptId: Long) : DeptEvent()
}