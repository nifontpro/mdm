plugins {
	id("multiplatform-compose-setup")
	id("android-setup")
}

kotlin {
	sourceSets {
		androidMain {
			dependencies {
				implementation(Dependencies.Image.Coil.core)
				implementation(Dependencies.Image.Coil.compose)
			}
		}
		commonMain {
			dependencies {
				implementation(project(":common:base:domain"))
				implementation(project(":common:event:domain"))
				implementation(project(":common:event:presenter"))
				implementation(project(":common:core-compose"))
				implementation(project(":common:core-utils"))

				implementation(Dependencies.Other.ViewModel.core)
				implementation(Dependencies.Other.ViewModel.compose)
				implementation(Dependencies.Other.ViewModel.odyssey)

				implementation(Dependencies.Other.Navigation.core)
				implementation(Dependencies.Other.Navigation.compose)
			}
		}
	}
}