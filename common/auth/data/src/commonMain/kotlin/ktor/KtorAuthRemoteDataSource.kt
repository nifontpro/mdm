package ktor

import base.BaseResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.HttpStatusCode
import kotlinx.serialization.Serializable
import logger.KLog
import user.User

class KtorAuthRemoteDataSource(
	private val httpClient: HttpClient
) {

	suspend fun profiles(): BaseResponse<List<User>> {
		val response = httpClient.post(clientUserUrl("profiles")) {
			setBody(EmptyBody())
		}
		return if (response.status == HttpStatusCode.OK) {
			response.body()
		} else {
			KLog.e("OAuth", "Error ${response.status.description}")
			BaseResponse.error(emptyList())

		}
	}

	suspend fun testAuth(): Boolean {
		val response = httpClient.post(clientUserUrl("data")) {
			setBody(RS("auth"))
		}

		KLog.d("OAuth", "Login status: ${response.status}")
		return response.status != HttpStatusCode.Unauthorized
	}
}

@Serializable
data class EmptyBody(
	val field: String = ""
)

const val HOST = "https://nmedalist.ru:8765"
const val CLIENT_URL = "$HOST/client"

fun clientUserUrl(patch: String) = "$CLIENT_URL/user/$patch"

@Serializable
private data class RS(
	val res: String
)