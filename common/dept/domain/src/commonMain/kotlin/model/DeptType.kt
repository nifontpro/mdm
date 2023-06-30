package model

@Suppress("unused")
enum class DeptType(val code: String) {
	ROOT("R"),
	USER_OWNER("U"),
	SIMPLE("D"),
	UNDEF("N")
}