package ktor

import auth.repo.AuthSettings
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
import io.ktor.http.parameters
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import logger.KLog
import model.TokensModel
import model.toTokens
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.singleton

internal val ktorModule = DI.Module("ktorModule") {

	bind<HttpClient>() with singleton {

		val authSettings: AuthSettings = Inject.instance()
//		val settings = Settings()

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
						val tokens = authSettings.getTokens()
						KLog.i("OAuth: Load tokens", tokens.toString())
						BearerTokens(
							accessToken = tokens.accessToken,
							refreshToken = tokens.refreshToken
						)
					}

					refreshTokens {
						val refreshToken = authSettings.getRefreshToken()
						KLog.e("OAuth: Get refresh from local", refreshToken)
						if (refreshToken.isBlank()) return@refreshTokens null

						val refreshResponse: TokensModel = client.submitForm(
							url = "$BASE_URL/token",
							formParameters = parameters {
								append("grant_type", REFRESH_TOKEN)
								append("client_id", CLIENT_ID)
								append(REFRESH_TOKEN, refreshToken)
							}
						) { markAsRefreshTokenRequest() }.body()

						KLog.e("OAuth: Response", refreshResponse.toString())

						authSettings.saveTokens(refreshResponse.toTokens())
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

private const val AUTH_HOST = "https://nmedalist.ru:9443"
private const val BASE_URL = "$AUTH_HOST/realms/medalist-realm/protocol/openid-connect"
private const val REFRESH_TOKEN = "refresh_token"
private const val CLIENT_ID = "medalist-client"