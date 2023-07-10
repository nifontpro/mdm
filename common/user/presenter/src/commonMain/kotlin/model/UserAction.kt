package model

sealed class UserAction {
	data class Test(val message: String) : UserAction()
}