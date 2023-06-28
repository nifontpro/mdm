package auth.model

data class Tokens(
	val accessToken: String = "",
	val refreshToken: String = "",
	val idToken: String = ""
)
