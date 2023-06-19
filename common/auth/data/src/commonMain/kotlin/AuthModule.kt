import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.provider
import settings.SettingsAuthDataSource
import settings.SettingsAuthDataSourceImpl

val authModule = DI.Module("authModule") {
//    bind<AuthRepository>() with singleton {
//        AuthRepositoryImpl(instance(), instance())
//    }

	bind<SettingsAuthDataSource>() with provider {
		SettingsAuthDataSourceImpl(instance())
	}

//    bind<KtorAuthRemoteDataSource>() with provider {
//        KtorAuthRemoteDataSource(instance())
//    }
}