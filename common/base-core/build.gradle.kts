plugins {
	id("multiplatform-setup")
	id("android-setup")
}

kotlin {
	sourceSets {
		commonMain {
			dependencies {
				implementation(project(":common:core"))
				implementation(project(":common:core-utils"))

				api(project(":common:settings:data"))
				implementation(project(":common:auth:data"))
				implementation(project(":common:dept:data"))

				implementation(Dependencies.Kodein.core)
			}
		}
	}
}