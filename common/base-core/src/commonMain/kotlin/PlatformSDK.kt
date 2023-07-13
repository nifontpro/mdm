import di.*
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.direct
import org.kodein.di.singleton

object PlatformSDK {

	fun init(
		configuration: PlatformConfiguration
	) {

		val baseModule = DI.Module(
			name = "base",
			init = {
				bind<PlatformConfiguration>() with singleton { configuration }
			}
		)

		Inject.createDependencies(
			DI {
				importAll(
					baseModule,
					settingsModule,
					coreModule,
					authModule,
					deptDataModule,
					deptDomainModule,
					userDataModule,
					userDomainModule,
					eventDataModule,
					eventDomainModule
				)
			}.direct
		)
	}
}