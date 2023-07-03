package repo

import auth.repo.AuthSettings
import curent.repo.CurrentSettings
import ktor.AuthRemoteDataSource
import model.request.GetAuthDeptIdRequest
import model.response.BaseResponse
import user.User

class AuthRepositoryImpl(
	private val remoteDataSource: AuthRemoteDataSource,
	private val authSettings: AuthSettings,
	private val currentSettings: CurrentSettings,
) : AuthRepository {

	override suspend fun getProfiles(): BaseResponse<List<User>> {
		return remoteDataSource.profiles()
	}

	override suspend fun getAuthDeptId(request: GetAuthDeptIdRequest): BaseResponse<Long> {
		val deptId = currentSettings.getCurrentDeptId()
		return if (deptId != 0L) {
			BaseResponse.success(data = deptId)
		} else {
			remoteDataSource.getAuthDeptId(request = request)
		}
	}

	override suspend fun isUserLoggedIn(): Boolean {
		return authSettings.getRefreshToken().isNotBlank()
	}
}