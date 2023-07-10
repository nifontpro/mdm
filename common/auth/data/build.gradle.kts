plugins {
	id("multiplatform-setup")
	id("android-setup")
	kotlin("plugin.serialization")
}

kotlin {
	sourceSets {
		commonMain {
			dependencies {
				implementation(project(":common:core"))
				implementation(project(":common:auth:domain"))
				implementation(project(":common:base:domain"))
				implementation(project(":common:user:domain"))

				implementation(Dependencies.Kotlin.Serialization.serialization)
				implementation(Dependencies.Kodein.core)
				implementation(Dependencies.Settings.core)
			}
		}
	}
}