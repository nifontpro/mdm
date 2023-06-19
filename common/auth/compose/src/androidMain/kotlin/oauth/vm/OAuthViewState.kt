package oauth.vm

import settings.model.TokensModel

data class OAuthViewState(
	val isAuth: Boolean = false,
	val tokens: TokensModel
)