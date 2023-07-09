package models

sealed class DeptEvent {
	object OnResume : DeptEvent()
	object OnTopLevel : DeptEvent()
	data class OnTest(val message: String) : DeptEvent()
	object Clear: DeptEvent()
	data class CurrentDeptIdChanged(val currentDeptId: Long) : DeptEvent()
	data class ClickDept(val deptId: Long) : DeptEvent()
}