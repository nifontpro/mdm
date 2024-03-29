package auth.repo

import auth.model.Tokens

interface AuthSettings {
	fun saveTokens(tokens: Tokens)
	fun getTokens(): Tokens
	fun logout()
	fun saveAccessToken(accessToken: String)
	fun getAccessToken(): String
	fun saveRefreshToken(refreshToken: String)
	fun getRefreshToken(): String
	fun saveIdToken(refreshToken: String)
	fun getIdToken(): String
}