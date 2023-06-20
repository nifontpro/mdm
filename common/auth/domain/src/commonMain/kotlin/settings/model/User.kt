package settings.model

import kotlinx.serialization.Serializable

@Serializable
data class User(
	val id: Long = 0,
	val firstname: String = "",
	val patronymic: String? = null,
	val lastname: String? = null,
	val authEmail: String? = null,
	val post: String? = null,
	val mainImg: String? = null,
)