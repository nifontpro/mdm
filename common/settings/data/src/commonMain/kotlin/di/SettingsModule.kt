package di

import auth.repo.SettingsAuth
import auth.repo.SettingsAuthImpl
import com.russhwolf.settings.Settings
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton

val settingsModule = DI.Module("di.getSettingsModule") {
	bind<Settings>() with singleton { Settings() }

	bind<SettingsAuth>() with singleton {
		SettingsAuthImpl(instance())
	}
}