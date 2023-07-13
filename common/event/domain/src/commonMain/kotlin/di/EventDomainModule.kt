package di

import biz.proc.EventProcessor
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.singleton

val eventDomainModule = DI.Module("eventDomainModule") {
	bind<EventProcessor>() with singleton {
		EventProcessor()
	}
}