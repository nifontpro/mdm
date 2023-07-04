package di

import biz.DeptProcessor
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.provider

val deptDomainModule = DI.Module("deptDomainModule") {
	bind<DeptProcessor>() with provider {
		DeptProcessor()
	}
}