package logger

import android.util.Log

actual class KLog {
	actual companion object {
		actual fun d(tag: String, message: String) {
			Log.d(tag, message)
		}

		actual fun i(tag: String, message: String) {
			Log.i(tag, message)
		}

		actual fun e(tag: String, message: String) {
			Log.e(tag, message)
		}
	}
}