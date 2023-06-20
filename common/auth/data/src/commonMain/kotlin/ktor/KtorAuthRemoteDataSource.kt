package ktor

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import kotlinx.serialization.Serializable
import settings.model.User

class KtorAuthRemoteDataSource(
	private val httpClient: HttpClient
) {

	suspend fun profiles(): List<User> {
		return try {
			httpClient.post("https://nmedalist.ru:8765/client/user/profiles") {
				setBody(EmptyBody())
			}.body()
		} catch (e: Exception) {
			e.printStackTrace()
			println("---> ERROR ${e.message}")
			emptyList()
		}
	}
}

@Serializable
data class EmptyBody(
	val field: String = ""
)