package model.request

import kotlinx.serialization.Serializable

@Serializable
data class BaseOrder(
    val field: String,
    val direction: Direction = Direction.ASC,
)

@Suppress("unused")
enum class Direction {
	ASC,
	DESC
}
