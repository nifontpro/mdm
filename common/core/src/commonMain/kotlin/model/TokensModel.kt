package model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TokensModel(
	@SerialName("access_token") val accessToken: String = "",
	@SerialName("expires_in") val expiresIn: Int = 0,
	@SerialName("refresh_token") val refreshToken: String? = null,
	@SerialName("scope") val scope: String = "",
	@SerialName("token_type") val tokenType: String = "",
	@SerialName("id_token") val idToken: String = "",
)