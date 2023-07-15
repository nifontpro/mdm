package models

import auth.model.Tokens

sealed class AuthEvent {
	data class SaveTokens(val value: Tokens) : AuthEvent()
	object RemoveTokens : AuthEvent()
	object ClearAction : AuthEvent()
	object MainFlowClick : AuthEvent()
	object LoginClick : AuthEvent()
	object LogoutClick : AuthEvent()
}
