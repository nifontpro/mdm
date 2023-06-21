package oauth.vm

import android.util.Log
import com.adeo.kviewmodel.BaseSharedViewModel
import di.Inject
import kotlinx.coroutines.launch
import settings.AuthRepository
import settings.SettingsAuthDataSource
import settings.model.TokensModel

class OAuthViewModel : BaseSharedViewModel<OAuthViewState, OAuthAction, OAuthEvent>(
	initialState = OAuthViewState(isAuth = false, tokens = TokensModel())
) {

	private val authRepository: AuthRepository = Inject.instance()

	private val settingsAuthDataSource: SettingsAuthDataSource = Inject.instance()

	init {
		checkUserLoggedIn()
	}

	override fun obtainEvent(viewEvent: OAuthEvent) {
		when (viewEvent) {
			OAuthEvent.ClearAction -> viewAction = null
			OAuthEvent.OAuthClick -> openOAuth()
			is OAuthEvent.SaveTokens -> obtainSaveTokens(viewEvent.value)
			OAuthEvent.LoginClick -> loginAction()
			OAuthEvent.ShowTokensClick -> showTokensAction()
			OAuthEvent.LogoutClick -> logoutAction()
			OAuthEvent.RemoveTokens -> removeTokens()
			OAuthEvent.ProfilesClick -> getProfiles()
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
		viewState = viewState.copy(tokens = value, isAuth = true)
		viewModelScope.launch {
			settingsAuthDataSource.saveTokens(tokens = value)
		}
	}

	fun getIdToken(): String {
		return settingsAuthDataSource.getIdToken()
	}

	private fun loginAction() {
		viewAction = OAuthAction.LoginAction
	}

	private fun logoutAction() {
		viewAction = OAuthAction.LogoutAction
	}

	private fun removeTokens() {
		settingsAuthDataSource.removeTokens()
		viewState = viewState.copy(tokens = TokensModel(), isAuth = false)
	}

	private fun showTokensAction() {
		Log.d("OAuth", viewState.tokens.accessToken)
		Log.d("OAuth", viewState.tokens.refreshToken)
		Log.d("OAuth", viewState.tokens.idToken)
		viewAction = null
	}

	private fun getProfiles() {
		viewModelScope.launch {
			val response = authRepository.getProfiles()
			if (response.success) {
				response.data?.forEach {user->
					println(user)
				}
			}
		}
	}
}