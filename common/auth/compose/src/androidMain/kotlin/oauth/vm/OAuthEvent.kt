package oauth.vm

import settings.model.TokensModel

sealed class OAuthEvent {
	data class SaveTokens(val value: TokensModel) : OAuthEvent()
	data class SaveAccessToken(val value: String) : OAuthEvent()
	data class SaveRefreshToken(val value: String) : OAuthEvent()

	object LoginClick : OAuthEvent()
	object ShowTokensClick : OAuthEvent()
	object OAuthClick : OAuthEvent()
}
