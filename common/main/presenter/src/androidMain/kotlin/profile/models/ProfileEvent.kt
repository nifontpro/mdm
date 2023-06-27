package profile.models

sealed class ProfileEvent {
    object AuthClicked : ProfileEvent()
}