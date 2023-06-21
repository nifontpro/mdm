import ktor.KtorAuthRemoteDataSource
import settings.AuthRepository
import settings.SettingsAuthDataSource
import settings.model.User
import settings.model.base.BaseResponse

class AuthRepositoryImpl(
	private val remoteDataSource: KtorAuthRemoteDataSource,
	private val cacheDataSource: SettingsAuthDataSource
) : AuthRepository {

	override suspend fun getProfiles(): BaseResponse<List<User>> {
		return remoteDataSource.profiles()
	}

}