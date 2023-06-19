package oauth.repo

import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import net.openid.appauth.AuthorizationException
import net.openid.appauth.AuthorizationResponse
import net.openid.appauth.AuthorizationService
import oauth.vm.OAuthEvent
import oauth.vm.OAuthViewModel

@Composable
fun oauthLauncher(
	appAuth: AppAuth,
	authService: AuthorizationService,
	viewModel: OAuthViewModel
) = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) {
	val intent = it.data ?: return@rememberLauncherForActivityResult
	val exception = AuthorizationException.fromIntent(intent)
	val tokenRequest = AuthorizationResponse.fromIntent(intent)?.createTokenExchangeRequest()
	when {
		exception != null -> Log.e("OAuth", "Authentication error")
		tokenRequest != null -> {
			Log.d("OAuth", "3. Received code = ${tokenRequest.authorizationCode}")

			CoroutineScope(Dispatchers.IO).launch {
				runCatching {
					Log.d(
						"OAuth",
						"4. Change code to token. Url = ${tokenRequest.configuration.tokenEndpoint}, verifier = ${tokenRequest.codeVerifier}"
					)
					val tokens = appAuth.performTokenRequestSuspend(authService, tokenRequest)
					viewModel.obtainEvent(OAuthEvent.SaveTokens(tokens))
				}.onSuccess {
					Log.d("OAuth", "5. Authentication successful!")
				}.onFailure {
					Log.e("OAuth", "Authentication error")
				}
			}
		}
	}
}