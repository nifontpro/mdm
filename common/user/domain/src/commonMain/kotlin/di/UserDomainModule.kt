package di

import biz.proc.UserProcessor
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.singleton

val userDomainModule = DI.Module("userDomainModule") {
	bind<UserProcessor>() with singleton {
		UserProcessor()
	}
}