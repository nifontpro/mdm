package model

import auth.model.Tokens

fun TokensModel.toTokens() = Tokens(
	accessToken = accessToken,
	refreshToken = refreshToken ?: "",
	idToken = idToken
)