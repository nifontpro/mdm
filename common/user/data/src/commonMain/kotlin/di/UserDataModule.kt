package di

import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton
import repo.UserRepository
import repo.UserRepositoryImpl

val userDataModule = DI.Module("userDataModule") {
	bind<UserRepository>() with singleton {
		UserRepositoryImpl(httpClient = instance())
	}
}