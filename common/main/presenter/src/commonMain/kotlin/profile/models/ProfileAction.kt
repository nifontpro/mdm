package profile.models

sealed class ProfileAction {
	object Auth : ProfileAction()
}