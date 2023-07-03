package rest

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import logger.KLog
import model.Dept
import model.request.GetCurrentDeptsRequest
import model.response.BaseResponse
import url.CLIENT_URL

class DeptRemoteDataSource(private val httpClient: HttpClient) {

	/**
	 * Получение списка отделов по parentId отдела
	 */
	suspend fun getCurrentDepts(request: GetCurrentDeptsRequest): BaseResponse<List<Dept>> {
		val response = httpClient.post(deptUrl("current_list")) {
			setBody(request)
		}
		return if (response.status == HttpStatusCode.OK) {
			response.body()
		} else {
			KLog.e("Rest: Dept", response.status.description)
			BaseResponse.error(emptyList())
		}
	}

	companion object {
		private fun deptUrl(patch: String) = "$CLIENT_URL/dept/$patch"
	}

}