package repo

import biz.helper.restError
import biz.proc.AuthContext.Companion.REPO
import constants.CLIENT_URL
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import model.RS
import model.response.BaseResponse

class AuthRepositoryImpl(
	private val httpClient: HttpClient
) : AuthRepository {

	override suspend fun checkAuth(): BaseResponse<RS> {
		val data = httpClient.post(userUrl("data")) {
			setBody(RS("auth"))
		}
		return when (data.status) {
			HttpStatusCode.OK -> BaseResponse.success(data = data.body())
			HttpStatusCode.Unauthorized -> BaseResponse.error(
				errors = listOf(
					restError(
						description = "Необходимо авторизоваться",
						repository = REPO,
						violationCode = data.status.description
					)
				)
			)

			else -> BaseResponse.error(
				errors = listOf(
					restError(
						description = "Ошибка соединения с сервером, проверьте подключение к интернету",
						repository = REPO,
						violationCode = data.status.description
					)
				)
			)
		}
	}

	companion object {
		private fun userUrl(patch: String) = "$CLIENT_URL/user/$patch"
	}
}