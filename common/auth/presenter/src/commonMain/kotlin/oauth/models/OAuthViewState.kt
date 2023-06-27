package oauth.models

import model.Tokens

data class OAuthViewState(
	val isAuth: Boolean = false,
	val tokens: Tokens
)