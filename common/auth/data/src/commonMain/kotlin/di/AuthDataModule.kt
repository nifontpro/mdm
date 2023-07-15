package di

import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton
import repo.AuthRepository
import repo.AuthRepositoryImpl

val authDataModule = DI.Module("authDataModule") {
	bind<AuthRepository>() with singleton {
		AuthRepositoryImpl(httpClient = instance())
	}
}