package di

import biz.proc.AuthProcessor
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.singleton

val authDomainModule = DI.Module("authDomainModule") {
	bind<AuthProcessor>() with singleton {
		AuthProcessor()
	}
}