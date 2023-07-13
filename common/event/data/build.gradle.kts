plugins {
	id("multiplatform-setup")
	id("android-setup")
	kotlin("plugin.serialization")
}

kotlin {
	sourceSets {
		commonMain {
			dependencies {
				implementation(project(":common:event:domain"))
				implementation(project(":common:core"))
				implementation(project(":common:base:domain"))

				implementation(Dependencies.Kotlin.Serialization.serialization)
				implementation(Dependencies.Kodein.core)
				implementation(Dependencies.Settings.core)
			}
		}
	}
}