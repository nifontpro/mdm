plugins {
	id("multiplatform-setup")
	id("android-setup")
}

kotlin {
	sourceSets {
		commonMain {
			dependencies {
				implementation(project(":common:main:domain"))
				implementation(project(":common:user:domain"))
				implementation(project(":common:core"))
				implementation(project(":common:base:domain"))
				implementation(project(":common:settings:domain"))

				implementation(Dependencies.Other.ViewModel.core)
			}
		}
	}
}