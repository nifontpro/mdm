package repo

import constants.CLIENT_URL
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.Serializable
import logger.KLog
import model.User
import model.request.GetUsersByDeptRequest
import model.response.BaseResponse

class UserRepositoryImpl(
	private val httpClient: HttpClient,
) : UserRepository {

	override suspend fun getProfiles(): BaseResponse<List<User>> {
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

	/**
	 * Получение списка сотрудников отдела
	 */
	override suspend fun getByDept(request: GetUsersByDeptRequest): BaseResponse<List<User>> {
		return httpClient.post(userUrl("get_by_dept")) {
			setBody(request)
		}.body()
	}

	companion object {
		private fun userUrl(patch: String) = "$CLIENT_URL/user/$patch"
	}

}

@Serializable
data class EmptyBody(
	val field: String = ""
)