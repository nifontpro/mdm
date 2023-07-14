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
				implementation(project(":common:dept:domain"))

				implementation(project(":common:user:data"))
				implementation(project(":common:user:domain"))

				implementation(project(":common:event:data"))
				implementation(project(":common:event:domain"))

				implementation(Dependencies.Kodein.core)
			}
		}
	}
}