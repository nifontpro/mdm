package rest

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import logger.KLog
import model.User
import model.request.GetUsersByDeptRequest
import model.response.BaseResponse
import constants.CLIENT_URL

class UserRemoteDataSource(private val httpClient: HttpClient) {

	/**
	 * Получение списка сотрудников отдела
	 */
	suspend fun getByDept(request: GetUsersByDeptRequest): BaseResponse<List<User>> {
		KLog.i("User", "request: $request")
		return httpClient.post(userUrl("get_by_dept")) {
			setBody(request)
		}.body()
	}

	companion object {
		private fun userUrl(patch: String) = "$CLIENT_URL/user/$patch"
	}

}