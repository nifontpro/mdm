package models

sealed class DeptAction {
	data class Test(val message: String) : DeptAction()
}