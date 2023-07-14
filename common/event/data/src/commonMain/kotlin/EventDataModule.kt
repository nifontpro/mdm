import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton
import repo.EventRepository
import repo.EventRepositoryImpl

val eventDataModule = DI.Module("eventDataModule") {

	bind<EventRepository>() with singleton {
		EventRepositoryImpl(httpClient = instance())
	}
}