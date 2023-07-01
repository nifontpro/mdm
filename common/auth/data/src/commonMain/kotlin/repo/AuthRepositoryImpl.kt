package repo

import auth.repo.AuthSettings
import ktor.AuthRemoteDataSource
import model.request.GetAuthParentIdRequest
import model.response.BaseResponse
import user.User

class AuthRepositoryImpl(
	private val remoteDataSource: AuthRemoteDataSource,
	private val authSettings: AuthSettings
) : AuthRepository {

	override suspend fun getProfiles(): BaseResponse<List<User>> {
		return remoteDataSource.profiles()
	}

	override suspend fun getAuthParentId(request: GetAuthParentIdRequest): BaseResponse<Long> {
		return remoteDataSource.getAuthParentId(request = request)
	}

	override suspend fun isUserLoggedIn(): Boolean {
		return authSettings.getRefreshToken().isNotBlank()
	}
}