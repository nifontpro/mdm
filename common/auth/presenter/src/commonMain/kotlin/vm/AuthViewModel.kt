package vm

import auth.model.Tokens
import auth.repo.AuthSettings
import biz.proc.AuthCommand
import biz.proc.AuthProcessor
import com.adeo.kviewmodel.BaseSharedViewModel
import di.Inject
import kotlinx.coroutines.launch
import mappers.toAuthContext
import mappers.toAuthViewState
import models.AuthAction
import models.AuthEvent
import models.AuthViewState

class AuthViewModel : BaseSharedViewModel<AuthViewState, AuthAction, AuthEvent>(
	initialState = AuthViewState()
) {

	private val authSettings: AuthSettings = Inject.instance()
	private val authProcessor: AuthProcessor = Inject.instance()

	init {
		getAuthState()
	}

	override fun obtainEvent(viewEvent: AuthEvent) {
		when (viewEvent) {
			is AuthEvent.SaveTokens -> obtainSaveTokens(viewEvent.value)
			AuthEvent.LoginClick -> viewAction = AuthAction.LoginAction
			AuthEvent.LogoutClick -> viewAction = AuthAction.LogoutAction
			AuthEvent.RemoveTokens -> removeTokens()
			AuthEvent.MainFlowClick -> mainFlowAction()
			AuthEvent.ClearAction -> viewAction = null
		}
	}

	private fun mainFlowAction() {
		viewAction = AuthAction.OpenMainFlow
	}

	private fun obtainSaveTokens(value: Tokens) {
		authSettings.saveTokens(tokens = value)
	}

	fun getIdToken(): String {
		return authSettings.getIdToken()
	}

	private fun removeTokens() {
		authSettings.logout()
	}

	private fun getAuthState() {
		viewModelScope.launch {
			viewState = viewState.copy(isLoading = true)
			val state = process(command = AuthCommand.GET_AUTH_STATE)
			if (state.isAuth) {
				mainFlowAction()
			} else {
				viewState = state
			}
		}
	}

	private suspend fun process(
		command: AuthCommand,
	): AuthViewState {
		val context = viewState.toAuthContext(command = command)
		authProcessor.exec(context)
		return context.toAuthViewState()
	}
}