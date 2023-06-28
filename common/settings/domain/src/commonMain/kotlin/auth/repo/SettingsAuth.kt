package auth.repo

import auth.model.Tokens

interface SettingsAuth {
	fun saveTokens(tokens: Tokens)
	fun getTokens(): Tokens
	fun removeTokens()
	fun saveAccessToken(accessToken: String)
	fun getAccessToken(): String
	fun saveRefreshToken(refreshToken: String)
	fun getRefreshToken(): String
	fun saveIdToken(refreshToken: String)
	fun getIdToken(): String
}