package ru.nb.mdm

import PlatformConfiguration
import android.app.Application

class MedalistApp: Application() {

    override fun onCreate() {
        super.onCreate()

        initPlatformSDK()
    }
}

fun MedalistApp.initPlatformSDK() =
    PlatformSDK.init(
        configuration = PlatformConfiguration(androidContext = applicationContext)
    )