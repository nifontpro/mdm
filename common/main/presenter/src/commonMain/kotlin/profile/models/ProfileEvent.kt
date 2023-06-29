package profile.models

sealed class ProfileEvent {
	object AuthClicked : ProfileEvent()
	data class AuthIdChanged(val authId: Long) : ProfileEvent()
}