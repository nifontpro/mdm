package oauth.vm

import settings.model.TokensModel

sealed class OAuthEvent {
	data class SaveTokens(val value: TokensModel) : OAuthEvent()
	object RemoveTokens : OAuthEvent()
	object ClearAction : OAuthEvent()
	object LoginClick : OAuthEvent()
	object LogoutClick : OAuthEvent()
	object ShowTokensClick : OAuthEvent()
	object ProfilesClick : OAuthEvent()
	object OAuthClick : OAuthEvent()
}
