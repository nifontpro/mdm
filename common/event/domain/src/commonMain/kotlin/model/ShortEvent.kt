package model

import kotlinx.serialization.Serializable

@Serializable
data class ShortEvent(
	val id: Long = 0,
	val eventDate: Long = 0,
	val days: Int = 0,
	val eventName: String = "",
)
