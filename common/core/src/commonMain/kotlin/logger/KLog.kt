package logger

expect class KLog {
	companion object {
		fun d(tag: String, message: String)
		fun i(tag: String, message: String)
		fun e(tag: String, message: String)
	}
}