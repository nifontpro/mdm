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
				implementation(project(":common:base:domain"))
				implementation(project(":common:user:domain"))
			}
		}
	}
}