package model.biz

import kotlinx.serialization.Serializable

@Serializable
data class ContextError(
	val code: String = "",
	val group: String = "",
	val field: String = "",
	val message: String = "",
	val level: Levels = Levels.ERROR,
) {

	@Suppress("unused")
	enum class Levels {
		ERROR,
		WARNING,
		INFO,
		UNAUTHORIZED
	}
}