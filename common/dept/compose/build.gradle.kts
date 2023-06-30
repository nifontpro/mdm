plugins {
	id("multiplatform-compose-setup")
	id("android-setup")
}

kotlin {
	sourceSets {
		androidMain {
			dependencies {
				implementation(Dependencies.Android.composeActivity)
			}
		}
		commonMain {
			dependencies {
				implementation(project(":common:dept:presenter"))
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