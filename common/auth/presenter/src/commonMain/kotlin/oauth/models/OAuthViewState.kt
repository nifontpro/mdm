package oauth.models

import auth.model.Tokens

data class OAuthViewState(
	val isAuth: Boolean = false,
	val tokens: Tokens
)