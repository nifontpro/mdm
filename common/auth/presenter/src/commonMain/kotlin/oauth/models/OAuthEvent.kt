package oauth.models

import auth.model.Tokens

sealed class OAuthEvent {
	data class EmailChanged(val value: String) :OAuthEvent()
	data class SaveTokens(val value: Tokens) : OAuthEvent()
	object RemoveTokens : OAuthEvent()
	object ClearAction : OAuthEvent()
	object MainFlowClick : OAuthEvent()
	object LoginClick : OAuthEvent()
	object LogoutClick : OAuthEvent()
	object ShowTokensClick : OAuthEvent()
	object ProfilesClick : OAuthEvent()
}
