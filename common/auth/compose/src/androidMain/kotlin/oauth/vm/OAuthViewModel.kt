package oauth.vm

import android.util.Log
import com.adeo.kviewmodel.BaseSharedViewModel
import di.Inject
import settings.SettingsAuthDataSource
import settings.model.TokensModel

class OAuthViewModel : BaseSharedViewModel<OAuthViewState, OAuthAction, OAuthEvent>(
	initialState = OAuthViewState(isAuth = false, tokens = TokensModel())
) {

	private val settingsAuthDataSource: SettingsAuthDataSource = Inject.instance()

	init {
		checkUserLoggedIn()
	}

	override fun obtainEvent(viewEvent: OAuthEvent) {
		when (viewEvent) {
			OAuthEvent.OAuthClick -> openOAuth()
			is OAuthEvent.SaveAccessToken -> obtainSaveAccessToken(viewEvent.value)
			is OAuthEvent.SaveRefreshToken -> obtainSaveRefreshToken(viewEvent.value)
			is OAuthEvent.SaveTokens -> obtainSaveTokens(viewEvent.value)
			OAuthEvent.LoginClick -> loginAction()
			OAuthEvent.ShowTokensClick -> showTokensAction()
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
		viewState = viewState.copy(tokens = value)
	}

	private fun obtainSaveRefreshToken(value: String) {
		settingsAuthDataSource.saveRefreshToken(refreshToken = value)
	}

	private fun loginAction() {
		viewAction = OAuthAction.LoginAction
	}

	private fun showTokensAction() {
		Log.d("AT", viewState.tokens.accessToken)
		Log.d("RT", viewState.tokens.refreshToken)
		Log.d("IT", viewState.tokens.idToken)
	}
}