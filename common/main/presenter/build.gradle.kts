plugins {
	id("multiplatform-setup")
	id("android-setup")
}

kotlin {
	sourceSets {
		commonMain {
			dependencies {
				api(project(":common:auth:domain"))
				api(project(":common:main:domain"))
				api(project(":common:core"))
				api(project(":common:base:domain"))

				implementation(Dependencies.Other.ViewModel.core)
			}
		}
	}
}