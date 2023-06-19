package oauth.vm

import PlatformConfiguration
import com.adeo.kviewmodel.BaseSharedViewModel
import di.Inject
import net.openid.appauth.AuthorizationService
import settings.model.TokensModel
import settings.SettingsAuthDataSource

class OAuthViewModel : BaseSharedViewModel<OAuthViewState, OAuthAction, OAuthEvent>(
	initialState = OAuthViewState(isAuth = false, tokens = TokensModel())
) {

	private val settingsAuthDataSource: SettingsAuthDataSource = Inject.instance()
	private val platformConfiguration: PlatformConfiguration = Inject.instance()
	private val authService: AuthorizationService = AuthorizationService(platformConfiguration.androidContext)

	init {
		checkUserLoggedIn()
	}

	override fun obtainEvent(viewEvent: OAuthEvent) {
		when (viewEvent) {
			OAuthEvent.OAuthClick -> openOAuth()
			is OAuthEvent.SaveAccessToken -> obtainSaveAccessToken(viewEvent.value)
			is OAuthEvent.SaveRefreshToken -> obtainSaveRefreshToken(viewEvent.value)
			is OAuthEvent.SaveTokens -> TODO()
			OAuthEvent.LoginClick -> loginAction()
		}
	}

	private fun checkUserLoggedIn() {
//        if (authRepository.isUserLoggedIn()) {
//            viewAction = LoginAction.OpenMainFlow
//        }
	}

	private fun openOAuth() {
//		viewAction = OAuthAction.OpenOAuthScreen
	}

	private fun obtainSaveAccessToken(value: String) {
		settingsAuthDataSource.saveAccessToken(accessToken = value)
	}

	private fun obtainSaveTokens(value: TokensModel) {
		settingsAuthDataSource.saveTokens(tokens = value)
	}

	private fun obtainSaveRefreshToken(value: String) {
		settingsAuthDataSource.saveRefreshToken(refreshToken = value)
	}

	private fun loginAction() {
		viewAction = OAuthAction.LoginAction
	}
}