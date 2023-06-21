package ktor

import com.russhwolf.settings.Settings
import com.russhwolf.settings.get
import di.Inject
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
import io.ktor.http.HttpStatusCode
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

		val settings: Settings = Inject.instance()

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
						val accessToken = settings[ACCESS_TOKEN, ""]
						val refreshToken = settings[REFRESH_TOKEN, ""]
						BearerTokens(accessToken = accessToken, refreshToken = refreshToken)
					}

					refreshTokens {
						val refreshToken = settings[REFRESH_TOKEN, ""]
						if (refreshToken.isBlank()) return@refreshTokens null

						val refreshResponse: TokenInfo = client.submitForm(
							url = "$BASE_URL/token",
							formParameters = parameters {
								append("grant_type", REFRESH_TOKEN)
								append("client_id", CLIENT_ID)
								append(REFRESH_TOKEN, refreshToken)
							}
						) { markAsRefreshTokenRequest() }.body()

						when (response.status) {
							HttpStatusCode.OK -> {
								settings.putString(ACCESS_TOKEN, refreshResponse.accessToken)
								settings.putString(REFRESH_TOKEN, refreshResponse.refreshToken ?: "")
								settings.putString(ID_TOKEN, refreshResponse.idToken)
							}
							HttpStatusCode.Unauthorized -> {
								println("---> Refresh: ${response.status.description}")
								settings.remove(ACCESS_TOKEN)
								settings.remove(REFRESH_TOKEN)
								settings.remove(ID_TOKEN)
								return@refreshTokens null
							}
						}

						refreshResponse.refreshToken?.let { rt ->
							BearerTokens(
								accessToken = refreshResponse.accessToken,
								refreshToken = rt
							)
						}

					}
				}
			}

			defaultRequest {
				header("Content-Type", "application/json; charset=UTF-8")
			}
		}
	}
}

private const val HOST = "https://nmedalist.ru:9443"
private const val BASE_URL = "$HOST/realms/medalist-realm/protocol/openid-connect"
private const val ACCESS_TOKEN = "access_token"
private const val REFRESH_TOKEN = "refresh_token"
private const val ID_TOKEN = "id_token"
private const val CLIENT_ID = "medalist-client"

@Serializable
data class TokenInfo(
	@SerialName("access_token") val accessToken: String = "",
	@SerialName("expires_in") val expiresIn: Int = 0,
	@SerialName("refresh_token") val refreshToken: String? = null,
	val scope: String = "",
	@SerialName("token_type") val tokenType: String = "",
	@SerialName("id_token") val idToken: String = "",
)