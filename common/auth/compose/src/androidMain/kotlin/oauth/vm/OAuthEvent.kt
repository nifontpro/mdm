package oauth.vm

import settings.model.TokensModel

sealed class OAuthEvent {
	data class SaveTokens(val value: TokensModel) : OAuthEvent()

	object ClearAction : OAuthEvent()
	object LoginClick : OAuthEvent()
	object ShowTokensClick : OAuthEvent()
	object OAuthClick : OAuthEvent()
}
