package settings

import model.Tokens

interface SettingsAuthDataSource {
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