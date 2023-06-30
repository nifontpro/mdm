package repo

import auth.repo.SettingsAuth
import ktor.AuthRemoteDataSource
import model.request.GetAuthParentIdRequest
import model.response.BaseResponse
import user.User

class AuthRepositoryImpl(
	private val remoteDataSource: AuthRemoteDataSource,
	private val settingsAuth: SettingsAuth
) : AuthRepository {

	override suspend fun getProfiles(): BaseResponse<List<User>> {
		return remoteDataSource.profiles()
	}

	override suspend fun getAuthParentId(request: GetAuthParentIdRequest): BaseResponse<Long> {
		return remoteDataSource.getAuthParentId(request = request)
	}

	override suspend fun isUserLoggedIn(): Boolean {
		return settingsAuth.getRefreshToken().isNotBlank()
	}
}