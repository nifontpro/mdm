package rest

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import logger.KLog
import model.Dept
import model.biz.ContextError
import model.request.GetAuthDeptRequest
import model.request.GetCurrentDeptsRequest
import model.response.BaseResponse
import url.CLIENT_URL

class DeptRemoteDataSource(private val httpClient: HttpClient) {

	suspend fun getAuthDept(request: GetAuthDeptRequest): BaseResponse<Dept> {
		val response = httpClient.post(deptUrl("get_auth_dept")) {
			setBody(request)
		}

		return if (response.status == HttpStatusCode.OK) {
			response.body()
		} else {
			KLog.e("Rest: Dept", response.status.description)
			BaseResponse.error(emptyList())
		}
	}

	/**
	 * Получение списка отделов по parentId отдела
	 */
	suspend fun getCurrentDepts(request: GetCurrentDeptsRequest): BaseResponse<List<Dept>> {
		val response = httpClient.post(deptUrl("current_list")) {
			setBody(request)
		}

		return if (response.status == HttpStatusCode.OK) {
			try {
				response.body()
			} catch (e: Exception) {
				BaseResponse.error(errors = listOf(ContextError(message = e.message.toString())))
			}
		} else {
			KLog.e("Rest: Dept", response.status.description)
			BaseResponse.error(emptyList())
		}
	}

	companion object {
		private fun deptUrl(patch: String) = "$CLIENT_URL/dept/$patch"
	}

}