package repo

import auth.repo.AuthSettings
import ktor.AuthRemoteDataSource
import model.request.GetAuthDeptIdRequest
import model.response.BaseResponse
import user.User

class AuthRepositoryImpl(
	private val remoteDataSource: AuthRemoteDataSource,
	private val authSettings: AuthSettings
) : AuthRepository {

	override suspend fun getProfiles(): BaseResponse<List<User>> {
		return remoteDataSource.profiles()
	}

	override suspend fun getAuthDeptId(request: GetAuthDeptIdRequest): BaseResponse<Long> {
		return remoteDataSource.getAuthDeptId(request = request)
	}

	override suspend fun isUserLoggedIn(): Boolean {
		return authSettings.getRefreshToken().isNotBlank()
	}
}