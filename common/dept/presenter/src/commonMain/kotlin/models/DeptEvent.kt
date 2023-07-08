package models

sealed class DeptEvent {
	object OnResume : DeptEvent()
	data class CurrentDeptIdChanged(val currentDeptId: Long) : DeptEvent()
}