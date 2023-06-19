package oauth

import android.util.Log
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.adeo.kviewmodel.compose.observeAsState
import com.adeo.kviewmodel.odyssey.StoredViewModel
import navigation.NavigationTree
import net.openid.appauth.AuthorizationService
import oauth.repo.AppAuth
import oauth.repo.oauthLauncher
import oauth.vm.OAuthAction
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

			OAuthAction.LoginAction -> {
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
				launcher.launch(openAuthPageIntent)
				Log.d("OAuth", "2. Open auth page: ${authRequest.toUri()}")
			}
			null -> {}
		}
	}
}
