package models

sealed class AuthAction {
	object LoginAction : AuthAction()
	object LogoutAction : AuthAction()
	object OpenMainFlow : AuthAction()
}
