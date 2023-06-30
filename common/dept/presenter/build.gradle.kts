plugins {
	id("multiplatform-setup")
	id("android-setup")
}

kotlin {
	sourceSets {
		commonMain {
			dependencies {
				api(project(":common:dept:domain"))
				api(project(":common:base:domain"))
				api(project(":common:core"))

				implementation(Dependencies.Other.ViewModel.core)
			}
		}
	}
}