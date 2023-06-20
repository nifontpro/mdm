import ktor.KtorAuthRemoteDataSource
import settings.AuthRepository
import settings.SettingsAuthDataSource
import settings.model.User

class AuthRepositoryImpl(
	private val remoteDataSource: KtorAuthRemoteDataSource,
	private val cacheDataSource: SettingsAuthDataSource
) : AuthRepository {

	override suspend fun getProfiles(): List<User> {
		return remoteDataSource.profiles()
	}

}