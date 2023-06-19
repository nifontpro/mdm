plugins {
	id("multiplatform-compose-setup")
	id("android-setup")
}

kotlin {
	sourceSets {
		androidMain {
			dependencies {
				implementation("androidx.activity:activity-compose:1.7.2")
			}
		}
		commonMain {
			dependencies {
				implementation(project(":common:auth:presenter"))
				implementation(project(":common:core-compose"))
				implementation(project(":common:core-utils"))

				implementation(Dependencies.Other.ViewModel.core)
				implementation(Dependencies.Other.ViewModel.compose)
				implementation(Dependencies.Other.ViewModel.odyssey)

				implementation(Dependencies.Other.Navigation.core)
				implementation(Dependencies.Other.Navigation.compose)

				implementation ("net.openid:appauth:0.11.1")
			}
		}
	}
}