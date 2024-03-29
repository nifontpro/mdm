plugins {
	id("multiplatform-setup")
	id("android-setup")
	kotlin("plugin.serialization")
}

kotlin {
	sourceSets {
		val commonMain by getting {
			dependencies {
				api(project(":common:settings:domain"))

				api(Dependencies.Kotlin.Serialization.serialization)
				api(Dependencies.Kotlin.Coroutines.core)

				api(Dependencies.Ktor.core)
				implementation(Dependencies.Ktor.json)
				implementation(Dependencies.Ktor.auth)
				implementation(Dependencies.Ktor.serialization)
				implementation(Dependencies.Ktor.negotiation)
				implementation(Dependencies.Ktor.kotlinJson)
				implementation(Dependencies.Ktor.logging)

				api(Dependencies.Kodein.core)

//                api(Dependencies.SqlDelight.core)
			}
		}

		androidMain {
			dependencies {
				implementation(Dependencies.Ktor.android)
//                implementation(Dependencies.SqlDelight.android)
			}
		}

		val iosX64Main by getting
		val iosArm64Main by getting
		val iosSimulatorArm64Main by getting

		@Suppress("UNUSED_VARIABLE")
		val iosMain by creating {
			dependencies {
				implementation(Dependencies.Ktor.ios)
//                implementation(Dependencies.SqlDelight.ios)
			}
			dependsOn(commonMain)
			iosX64Main.dependsOn(this)
			iosArm64Main.dependsOn(this)
			iosSimulatorArm64Main.dependsOn(this)
		}

		desktopMain {
			dependencies {
				implementation(Dependencies.Ktor.okhttp)
//                implementation(Dependencies.SqlDelight.desktop)
			}
		}
	}
}