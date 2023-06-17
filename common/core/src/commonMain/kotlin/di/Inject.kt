package di

import org.kodein.di.DirectDI
import org.kodein.di.instance
import kotlin.native.concurrent.ThreadLocal

@ThreadLocal
object Inject {

	private var directDI: DirectDI? = null

	val di: DirectDI
		get() = requireNotNull(directDI)

	fun createDependencies(tree: DirectDI) {
		directDI = tree
	}

	inline fun <reified T> instance(): T {
		return di.instance()
	}
}