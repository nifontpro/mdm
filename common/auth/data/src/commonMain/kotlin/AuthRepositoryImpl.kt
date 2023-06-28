import auth.repo.SettingsAuth
import ktor.KtorAuthRemoteDataSource
import settings.AuthRepository
import user.User
import base.BaseResponse

class AuthRepositoryImpl(
	private val remoteDataSource: KtorAuthRemoteDataSource,
	private val cacheDataSource: SettingsAuth
) : AuthRepository {

	override suspend fun getProfiles(): BaseResponse<List<User>> {
		return remoteDataSource.profiles()
	}

	override suspend fun isUserLoggedIn(): Boolean {
		return cacheDataSource.getRefreshToken().isNotBlank()
	}
}