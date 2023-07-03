package di

import repo.AuthRepositoryImpl
import ktor.AuthRemoteDataSource
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.provider
import org.kodein.di.singleton
import repo.AuthRepository

val authModule = DI.Module("authModule") {
	bind<AuthRemoteDataSource>() with provider {
		AuthRemoteDataSource(httpClient = instance())
	}

	bind<AuthRepository>() with singleton {
		AuthRepositoryImpl(remoteDataSource = instance(), authSettings = instance(), currentSettings = instance())
	}
}