package di

import auth.repo.AuthSettings
import auth.repo.AuthSettingsImpl
import com.russhwolf.settings.Settings
import curent.CurrentSettingsImpl
import curent.repo.CurrentSettings
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton

val settingsModule = DI.Module("di.getSettingsModule") {
	bind<Settings>() with singleton { Settings() }

	bind<AuthSettings>() with singleton {
		AuthSettingsImpl(instance())
	}

	bind<CurrentSettings>() with singleton {
		CurrentSettingsImpl(instance())
	}
}