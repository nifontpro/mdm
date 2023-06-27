package settings

import com.russhwolf.settings.Settings
import com.russhwolf.settings.get
import logger.KLog
import model.Tokens

class SettingsAuthDataSourceImpl(
	private val settings: Settings
) : SettingsAuthDataSource {

	override fun saveTokens(tokens: Tokens) {
		settings.putString(ACCESS_TOKEN, tokens.accessToken)
		settings.putString(REFRESH_TOKEN, tokens.refreshToken)
		settings.putString(ID_TOKEN, tokens.idToken)
		KLog.d("OAuth-AT", tokens.accessToken)
		KLog.i("OAuth-RT", tokens.refreshToken)
	}

	override fun getTokens(): Tokens {
		return Tokens(
			accessToken = settings[ACCESS_TOKEN, ""],
			refreshToken = settings[REFRESH_TOKEN, ""],
			idToken = settings[ID_TOKEN, ""],
		)
	}

	override fun removeTokens() {
		settings.remove(ACCESS_TOKEN)
		settings.remove(REFRESH_TOKEN)
		settings.remove(ID_TOKEN)
	}

	override fun saveAccessToken(accessToken: String) {
		settings.putString(ACCESS_TOKEN, accessToken)
	}

	override fun getAccessToken(): String {
		return settings[ACCESS_TOKEN, ""]
	}

	override fun saveRefreshToken(refreshToken: String) {
		settings.putString(REFRESH_TOKEN, refreshToken)
	}

	override fun getRefreshToken(): String {
		return settings[REFRESH_TOKEN, ""]
	}

	override fun saveIdToken(refreshToken: String) {
		settings.putString(ID_TOKEN, refreshToken)
	}

	override fun getIdToken(): String {
		return settings[ID_TOKEN, ""]
	}

	companion object {
		private const val ACCESS_TOKEN = "access_token"
		private const val REFRESH_TOKEN = "refresh_token"
		private const val ID_TOKEN = "id_token"
	}
}