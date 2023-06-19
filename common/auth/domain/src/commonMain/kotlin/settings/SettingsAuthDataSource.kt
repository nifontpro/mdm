package settings

import settings.model.TokensModel

interface SettingsAuthDataSource {
	fun saveTokens(tokens: TokensModel)
	fun getTokens(): TokensModel
	fun removeTokens()
	fun saveAccessToken(accessToken: String)
	fun getAccessToken(): String
	fun saveRefreshToken(refreshToken: String)
	fun getRefreshToken(): String
	fun saveIdToken(refreshToken: String)
	fun getIdToken(): String
}