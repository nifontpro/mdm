package di

import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton
import repo.UserRepository
import repo.UserRepositoryImpl
import rest.UserRemoteDataSource

val userDataModule = DI.Module("userDataModule") {
	bind<UserRemoteDataSource>() with singleton {
		UserRemoteDataSource(httpClient = instance())
	}

	bind<UserRepository>() with singleton {
		UserRepositoryImpl(userRemoteDataSource = instance())
	}
}