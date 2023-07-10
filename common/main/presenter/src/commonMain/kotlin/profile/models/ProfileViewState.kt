package profile.models

import model.User

data class ProfileViewState(
	val username: String,
	val avatarUrl: String,
	val profiles: List<User>,
	val authId: Long,
)