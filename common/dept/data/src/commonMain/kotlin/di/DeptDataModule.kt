package di

import org.kodein.di.*
import repo.DeptRepository
import repo.DeptRepositoryImpl
import rest.DeptRemoteDataSource

val deptDataModule = DI.Module("deptDataModule") {
	bind<DeptRemoteDataSource>() with provider {
		DeptRemoteDataSource(httpClient = instance())
	}

	bind<DeptRepository>() with singleton {
		DeptRepositoryImpl(deptRemoteDataSource = instance(), currentSettings = instance())
	}
}