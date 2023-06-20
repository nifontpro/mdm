package ktor

import com.russhwolf.settings.Settings
import com.russhwolf.settings.get
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.client.request.forms.submitForm
import io.ktor.client.request.header
import io.ktor.http.parameters
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.singleton

internal val ktorModule = DI.Module("ktorModule") {

	bind<HttpClient>() with singleton {
		HttpClient(HttpEngineFactory().createEngine()) {
			install(Logging) {
				logger = Logger.SIMPLE
				level = LogLevel.ALL
			}

			install(DefaultRequest)

			install(ContentNegotiation) {
				json(Json {
					isLenient = true
					ignoreUnknownKeys = true
					prettyPrint = true
				})
			}

			install(HttpTimeout) {
				connectTimeoutMillis = 15000
				requestTimeoutMillis = 30000
			}

			// https://ktor.io/docs/bearer-client.html
			install(Auth) {
				bearer {
					loadTokens {
						val settings = Settings()
						val accessToken = settings["access_token", ""]
						val refreshToken = settings["refresh_token", ""]
						BearerTokens(accessToken = accessToken, refreshToken = refreshToken)
					}

					refreshTokens {
						val refreshTokenInfo: TokenInfo = client.submitForm(
							url = "https://accounts.google.com/o/oauth2/token",
							formParameters = parameters {
								append("grant_type", "refresh_token")
							}
						) { markAsRefreshTokenRequest() }.body()
						BearerTokens(
							accessToken = refreshTokenInfo.accessToken,
							refreshToken = refreshTokenInfo.refreshToken ?: ""
						)
					}

				}
//					BearerTokens(accessToken = "123", refreshToken = "456")
			}

			defaultRequest {
				header("Content-Type", "application/json; charset=UTF-8")
			}
		}
	}
}

@Serializable
data class TokenInfo(
	@SerialName("access_token") val accessToken: String,
	@SerialName("expires_in") val expiresIn: Int,
	@SerialName("refresh_token") val refreshToken: String? = null,
	val scope: String,
	@SerialName("token_type") val tokenType: String,
	@SerialName("id_token") val idToken: String,
)