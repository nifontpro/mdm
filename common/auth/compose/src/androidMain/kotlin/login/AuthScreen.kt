package login

import android.util.Log
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.adeo.kviewmodel.compose.observeAsState
import com.adeo.kviewmodel.odyssey.StoredViewModel
import navigation.NavigationTree
import net.openid.appauth.AuthorizationService
import login.repo.AppAuth
import login.repo.loginLauncher
import login.repo.logoutLauncher
import models.AuthAction
import models.AuthEvent
import ru.alexgladkov.odyssey.compose.extensions.present
import ru.alexgladkov.odyssey.compose.local.LocalRootController
import ru.alexgladkov.odyssey.core.LaunchFlag
import vm.AuthViewModel

@Composable
fun AuthScreen() {

	val rootController = LocalRootController.current

	StoredViewModel(factory = { AuthViewModel() }) { viewModel ->
		val state = viewModel.viewStates().observeAsState()
		val action = viewModel.viewActions().observeAsState()
		val eventHandler = viewModel::obtainEvent

		AuthView(
			state = state.value,
			eventHandler = eventHandler
		)

		val context = LocalContext.current
		val authService by remember {
			mutableStateOf(AuthorizationService(context))
		}
		val appAuth by remember {
			mutableStateOf(AppAuth())
		}
		val loginLauncher = loginLauncher(appAuth, authService, eventHandler)
		val logoutLauncher = logoutLauncher()

		LaunchedEffect(key1 = action.value) {
			when (action.value) {
				null -> {}
				AuthAction.OpenMainFlow -> {
					rootController.findRootController()
						.present(
							screen = NavigationTree.Main.Dashboard.name,
							launchFlag = LaunchFlag.SingleNewTask
						)
				}

				AuthAction.LoginAction -> {
					val customTabsIntent = CustomTabsIntent.Builder().build()
					val authRequest = appAuth.getAuthRequest()
					Log.d(
						"OAuth",
						"1. Generated verifier=${authRequest.codeVerifier}, challenge=${authRequest.codeVerifierChallenge}"
					)
					val openAuthPageIntent = authService.getAuthorizationRequestIntent(
						authRequest,
						customTabsIntent
					)
					loginLauncher.launch(openAuthPageIntent)
					Log.d("OAuth", "2. Open auth page: ${authRequest.toUri()}")
				}

				AuthAction.LogoutAction -> {
					val idToken = viewModel.getIdToken()
					if (idToken.isNotBlank()) {
						val customTabsIntent = CustomTabsIntent.Builder().build()
						val logoutPageIntent = authService.getEndSessionRequestIntent(
							appAuth.getEndSessionRequest(idToken),
							customTabsIntent
						)
						logoutLauncher.launch(logoutPageIntent)
					}
					eventHandler(AuthEvent.RemoveTokens)
				}
			}
		}
	}
}
