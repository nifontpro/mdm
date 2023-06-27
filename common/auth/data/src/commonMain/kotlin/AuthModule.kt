import ktor.KtorAuthRemoteDataSource
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.provider
import org.kodein.di.singleton
import settings.AuthRepository

val authModule = DI.Module("authModule") {
	bind<AuthRepository>() with singleton {
		AuthRepositoryImpl(remoteDataSource = instance(), cacheDataSource = instance())
	}

	bind<KtorAuthRemoteDataSource>() with provider {
		KtorAuthRemoteDataSource(httpClient = instance())
	}
}