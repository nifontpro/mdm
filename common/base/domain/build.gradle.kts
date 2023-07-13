plugins {
	id("multiplatform-setup")
	id("android-setup")
	kotlin("plugin.serialization")
}

kotlin {
	sourceSets {
		commonMain {
			dependencies {
				implementation(Dependencies.Kotlin.Serialization.serialization)
				implementation(Dependencies.Kodein.core)
				implementation(project(":common:core"))

				implementation(project(":common:cor"))
				implementation(project(":common:settings:domain"))
			}
		}
	}
}