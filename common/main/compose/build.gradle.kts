plugins {
	id("multiplatform-compose-setup")
	id("android-setup")
}

kotlin {
	sourceSets {
		commonMain {
			dependencies {
				implementation(project(":common:main:presenter"))
				implementation(project(":common:core-compose"))
				implementation(project(":common:core-utils"))
				implementation(project(":common:base:domain"))
				implementation(project(":common:user:domain"))

				implementation(Dependencies.Other.ViewModel.core)
				implementation(Dependencies.Other.ViewModel.compose)
				implementation(Dependencies.Other.ViewModel.odyssey)

				implementation(Dependencies.Other.Navigation.core)
				implementation(Dependencies.Other.Navigation.compose)
			}
		}

		androidMain {
			dependencies {
				implementation(project(":common:dept:compose"))
				implementation(project(":common:user:compose"))
				implementation(Dependencies.Image.Coil.core)
				implementation(Dependencies.Image.Coil.compose)
			}
		}
	}
}