package di

import biz.proc.DeptProcessor
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.singleton

val deptDomainModule = DI.Module("deptDomainModule") {
	bind<DeptProcessor>() with singleton {
		DeptProcessor()
	}
}