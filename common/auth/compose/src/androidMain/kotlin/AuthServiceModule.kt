import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.provider

val authServiceModule = DI.Module("authServiceModule") {

	bind<AuthServiceFactory>() with provider {
		AuthServiceFactory(instance())
	}

}