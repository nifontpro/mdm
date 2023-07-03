package ktor

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.Serializable
import logger.KLog
import model.request.GetAuthDeptIdRequest
import model.response.BaseResponse
import url.CLIENT_URL
import user.User

class AuthRemoteDataSource(
	private val httpClient: HttpClient,
) {

	suspend fun profiles(): BaseResponse<List<User>> {
		val response = httpClient.post(userUrl("profiles")) {
			setBody(EmptyBody())
		}
		return if (response.status == HttpStatusCode.OK) {
			response.body()
		} else {
			KLog.e("OAuth", "Error ${response.status.description}")
			BaseResponse.error(emptyList())
		}
	}

	suspend fun getAuthDeptId(request: GetAuthDeptIdRequest): BaseResponse<Long> {
		val response = httpClient.post(userUrl("get_dept_id")) {
			setBody(request)
		}
		return if (response.status == HttpStatusCode.OK) {
			response.body()
		} else {
			KLog.e("Rest", response.status.description)
			BaseResponse.error(emptyList())
		}
	}

	suspend fun testAuth(): Boolean {
		val response = httpClient.post(userUrl("data")) {
			setBody(RS("auth"))
		}

		KLog.d("OAuth", "Login status: ${response.status}")
		return response.status != HttpStatusCode.Unauthorized
	}

	companion object {
		private fun userUrl(patch: String) = "$CLIENT_URL/user/$patch"
	}
}

@Serializable
data class EmptyBody(
	val field: String = ""
)

@Serializable
private data class RS(
	val res: String
)