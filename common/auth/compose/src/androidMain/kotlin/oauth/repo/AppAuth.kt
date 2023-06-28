package oauth.repo

import android.net.Uri
import android.util.Log
import androidx.core.net.toUri
import auth.model.Tokens
import net.openid.appauth.AuthorizationRequest
import net.openid.appauth.AuthorizationService
import net.openid.appauth.AuthorizationServiceConfiguration
import net.openid.appauth.ClientAuthentication
import net.openid.appauth.ClientSecretPost
import net.openid.appauth.EndSessionRequest
import net.openid.appauth.ResponseTypeValues
import net.openid.appauth.TokenRequest
import kotlin.coroutines.suspendCoroutine

class AppAuth {

	private val serviceConfiguration = AuthorizationServiceConfiguration(
		Uri.parse(AUTH_URI),
		Uri.parse(TOKEN_URI),
		null, // registration endpoint
		Uri.parse(END_SESSION_URI)
	)

	fun getAuthRequest(): AuthorizationRequest {
		val redirectUri = CALLBACK_URL.toUri()

		return AuthorizationRequest.Builder(
			serviceConfiguration,
			CLIENT_ID,
			RESPONSE_TYPE,
			redirectUri
		)
			.setScope(SCOPE)
			.build()
	}

	fun getEndSessionRequest(idToken: String): EndSessionRequest {
		return EndSessionRequest.Builder(serviceConfiguration)
			.setIdTokenHint(idToken)
			.setPostLogoutRedirectUri(LOGOUT_CALLBACK_URL.toUri())
			.build()
	}

//	fun getRefreshTokenRequest(refreshToken: String): TokenRequest {
//		return TokenRequest.Builder(
//			serviceConfiguration,
//			CLIENT_ID
//		)
//			.setGrantType(GrantTypeValues.REFRESH_TOKEN)
//			.setScopes(SCOPE)
//			.setRefreshToken(refreshToken)
//			.build()
//	}

	suspend fun performTokenRequestSuspend(
		authService: AuthorizationService,
		tokenRequest: TokenRequest,
	): Tokens {
		return suspendCoroutine { continuation ->
			authService.performTokenRequest(tokenRequest, getClientAuthentication()) { response, ex ->
				Log.e("AppAuth", ex?.message ?: "")
				when {
					response != null -> {
						// получение токена произошло успешно
						val tokens = Tokens(
							accessToken = response.accessToken.orEmpty(),
							refreshToken = response.refreshToken.orEmpty(),
							idToken = response.idToken.orEmpty()
						)
						continuation.resumeWith(Result.success(tokens))
					}
					// получение токенов произошло неуспешно, показываем ошибку
					ex != null -> {
						continuation.resumeWith(Result.failure(ex))
					}

					else -> error("unreachable")
				}
			}
		}
	}

	private fun getClientAuthentication(): ClientAuthentication {
		return ClientSecretPost(CLIENT_SECRET)
	}

	companion object {
		private const val HOST = "https://nmedalist.ru:9443/"
		private const val BASE_URL = "${HOST}realms/medalist-realm/protocol/openid-connect/"
		const val AUTH_URI = "${BASE_URL}auth"
		const val TOKEN_URI = "${BASE_URL}token"
		const val END_SESSION_URI = "${BASE_URL}logout"
		private const val RESPONSE_TYPE = ResponseTypeValues.CODE
		private const val SCOPE = "openid"
		private const val CLIENT_ID = "medalist-client"
		private const val CLIENT_SECRET = "secret"
		private const val CALLBACK_URL = "ru.nb.mdm://login"
		private const val LOGOUT_CALLBACK_URL = "ru.nb.mdm://logout"
	}
}