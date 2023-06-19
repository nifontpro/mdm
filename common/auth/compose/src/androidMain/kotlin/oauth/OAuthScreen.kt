package oauth

import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.adeo.kviewmodel.compose.observeAsState
import com.adeo.kviewmodel.odyssey.StoredViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import navigation.NavigationTree
import net.openid.appauth.AuthorizationException
import net.openid.appauth.AuthorizationResponse
import net.openid.appauth.AuthorizationService
import oauth.repo.AppAuth
import oauth.vm.OAuthAction
import oauth.vm.OAuthEvent
import oauth.vm.OAuthViewModel
import ru.alexgladkov.odyssey.compose.extensions.present
import ru.alexgladkov.odyssey.compose.local.LocalRootController
import ru.alexgladkov.odyssey.core.LaunchFlag

@Composable
fun OAuthScreen() {

	val rootController = LocalRootController.current

	StoredViewModel(factory = { OAuthViewModel() }) { viewModel ->
		val state = viewModel.viewStates().observeAsState()
		val action = viewModel.viewActions().observeAsState()

		val context = LocalContext.current
		val authService by remember {
			mutableStateOf(AuthorizationService(context))
		}
		val appAuth by remember {
			mutableStateOf(AppAuth())
		}

		val launcher = oauthLauncher(appAuth, authService, viewModel)

		OAuthView(
			state = state.value,
			eventHandler = viewModel::obtainEvent
		)

		when (action.value) {
			OAuthAction.OpenMainFlow -> {
				rootController.findRootController()
					.present(
						screen = NavigationTree.Main.Dashboard.name,
						launchFlag = LaunchFlag.SingleNewTask
					)
			}

			null -> {}
			OAuthAction.LoginAction -> {
				val customTabsIntent = CustomTabsIntent.Builder().build()
				val authRequest = appAuth.getAuthRequest()
				Log.d(
					"OAuth",
					"1. Generated verifier=${authRequest.codeVerifier},challenge=${authRequest.codeVerifierChallenge}"
				)
				val openAuthPageIntent = authService.getAuthorizationRequestIntent(
					authRequest,
					customTabsIntent
				)
				launcher.launch(openAuthPageIntent)
				Log.d("OAuth", "2. Open auth page: ${authRequest.toUri()}")
			}
		}
	}
}

@Composable
private fun oauthLauncher(
	appAuth: AppAuth,
	authService: AuthorizationService,
	viewModel: OAuthViewModel
) = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) {
	val intent = it.data ?: return@rememberLauncherForActivityResult
	val exception = AuthorizationException.fromIntent(intent)
	// пытаемся получить запрос для обмена кода на токен, null - если произошла ошибка
	val tokenRequest = AuthorizationResponse.fromIntent(intent)?.createTokenExchangeRequest()
	when {
		// авторизация завершались ошибкой
		exception != null -> Log.e("OAuth", "Авторизация завершилась с ошибкой}")
		// авторизация прошла успешно, меняем код на токен
		tokenRequest != null -> {
			Log.d("OAuth", "3. Received code = ${tokenRequest.authorizationCode}")

			CoroutineScope(Dispatchers.IO).launch {
//					loadingMutableStateFlow.value = true
				runCatching {
					Log.d(
						"OAuth",
						"4. Change code to token. Url = ${tokenRequest.configuration.tokenEndpoint}, verifier = ${tokenRequest.codeVerifier}"
					)
					val tokens = appAuth.performTokenRequestSuspend(authService, tokenRequest)
					viewModel.obtainEvent(OAuthEvent.SaveTokens(tokens))
				}.onSuccess {
//						loadingMutableStateFlow.value = false
					Log.d("OAuth", "Аутентификация прошла успешно!!!")
				}.onFailure {
//						loadingMutableStateFlow.value = false

					Log.d("OAuth", "Аутентификация не выполнена")
				}
			}
		}
	}
}
