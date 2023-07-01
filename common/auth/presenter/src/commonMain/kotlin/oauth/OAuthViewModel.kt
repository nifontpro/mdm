package oauth

import auth.repo.AuthSettings
import model.response.BaseResponse
import com.adeo.kviewmodel.BaseSharedViewModel
import di.Inject
import kotlinx.coroutines.launch
import logger.KLog
import auth.model.Tokens
import oauth.models.OAuthAction
import oauth.models.OAuthEvent
import oauth.models.OAuthViewState
import repo.AuthRepository
import user.User

class OAuthViewModel : BaseSharedViewModel<OAuthViewState, OAuthAction, OAuthEvent>(
	initialState = OAuthViewState(isAuth = false, tokens = Tokens())
) {

	private val authRepository: AuthRepository = Inject.instance()
	private val authSettings: AuthSettings = Inject.instance()

	override fun obtainEvent(viewEvent: OAuthEvent) {
		when (viewEvent) {
			OAuthEvent.ClearAction -> viewAction = null
			is OAuthEvent.SaveTokens -> obtainSaveTokens(viewEvent.value)
			OAuthEvent.LoginClick -> loginAction()
			OAuthEvent.ShowTokensClick -> showTokensAction()
			OAuthEvent.LogoutClick -> logoutAction()
			OAuthEvent.RemoveTokens -> removeTokens()
			OAuthEvent.ProfilesClick -> getProfiles()
			OAuthEvent.MainFlowClick -> mainFlowAction()
		}
	}

	private fun mainFlowAction() {
		viewAction = OAuthAction.OpenMainFlow
	}

	private fun obtainSaveTokens(value: Tokens) {
		viewState = viewState.copy(tokens = value, isAuth = true)
		viewModelScope.launch {
			authSettings.saveTokens(tokens = value)
		}
	}

	fun getIdToken(): String {
		return authSettings.getIdToken()
	}

	private fun loginAction() {
		viewAction = OAuthAction.LoginAction
	}

	private fun logoutAction() {
		viewAction = OAuthAction.LogoutAction
	}

	private fun removeTokens() {
		authSettings.logout()
		viewState = viewState.copy(tokens = Tokens(), isAuth = false)
	}

	private fun showTokensAction() {
		val tokens = authSettings.getTokens()
		KLog.i("OAuth: AT", tokens.accessToken)
		KLog.i("OAuth: RT", tokens.refreshToken)
		KLog.i("OAuth: IT", tokens.idToken)
		viewAction = null
	}

	private fun getProfiles() {
		viewModelScope.launch {
			val response: BaseResponse<List<User>> = authRepository.getProfiles()
			if (response.success) {
				response.data?.forEach { user ->
					println(user)
				}
			}
		}
	}
}