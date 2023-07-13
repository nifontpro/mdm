plugins {
	id("multiplatform-setup")
	id("android-setup")
}

kotlin {
	sourceSets {
		commonMain {
			dependencies {
//				implementation(project(":common:auth:domain"))
				implementation(project(":common:event:domain"))
				implementation(project(":common:base:domain"))
				implementation(project(":common:core"))

				implementation(Dependencies.Other.ViewModel.core)
			}
		}
	}
}