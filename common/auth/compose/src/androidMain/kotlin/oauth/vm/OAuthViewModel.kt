package oauth.vm

import android.util.Log
import com.adeo.kviewmodel.BaseSharedViewModel
import di.Inject
import kotlinx.coroutines.launch
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
			is OAuthEvent.SaveTokens -> obtainSaveTokens(viewEvent.value)
			OAuthEvent.LoginClick -> loginAction()
			OAuthEvent.ShowTokensClick -> showTokensAction()
			OAuthEvent.ClearAction -> viewAction = null
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

	private fun obtainSaveTokens(value: TokensModel) {
		viewAction = null
		viewState = viewState.copy(tokens = value, isAuth = true)
		viewModelScope.launch {
			settingsAuthDataSource.saveTokens(tokens = value)
		}
	}

	private fun loginAction() {
		viewAction = OAuthAction.LoginAction
	}

	private fun showTokensAction() {
		Log.d("OAuth", viewState.tokens.accessToken)
		Log.d("OAuth", viewState.tokens.refreshToken)
		Log.d("OAuth", viewState.tokens.idToken)
	}
}