package profile

import auth.repo.SettingsAuth
import com.adeo.kviewmodel.BaseSharedViewModel
import di.Inject
import kotlinx.coroutines.launch
import profile.models.ProfileAction
import profile.models.ProfileEvent
import profile.models.ProfileViewState
import repo.AuthRepository

class ProfileViewModel : BaseSharedViewModel<ProfileViewState, ProfileAction, ProfileEvent>(
	initialState = ProfileViewState(
		authId = 0,
		username = "Nifont Busygin",
		avatarUrl = "https://static01.nyt.com/images/2022/09/16/arts/16CAMERON2/merlin_32238772_5ba78554-6e17-4091-b4cb-5b78d64086de-mobileMasterAt3x.jpg",
		profiles = emptyList()
	)
) {
	private val authRepository: AuthRepository = Inject.instance()
	private val settingsAuth: SettingsAuth = Inject.instance()

	init {
		getProfiles()
		getAuthId()
	}

	override fun obtainEvent(viewEvent: ProfileEvent) {
		when (viewEvent) {
			ProfileEvent.AuthClicked -> auth()
			is ProfileEvent.AuthIdChanged -> obtainAuthIdChanged(viewEvent.authId)
		}
	}

	private fun auth() {
		viewAction = ProfileAction.Auth
	}

	private fun obtainAuthIdChanged(authId: Long) {
		viewModelScope.launch {
			viewState = viewState.copy(authId = authId)
			settingsAuth.saveAuthId(authId)
		}
	}

	private fun getProfiles() {
		viewModelScope.launch {
			val response = authRepository.getProfiles()
			if (response.success) {
				response.data?.let {
					viewState = viewState.copy(profiles = it)
				}
			}
		}
	}

	private fun getAuthId() {
		val authId = settingsAuth.getAuthId()
		viewState = viewState.copy(authId = authId)
	}

}