package model

fun TokensModel.toTokens() = Tokens(
	accessToken = accessToken,
	refreshToken = refreshToken ?: "",
	idToken = idToken
)