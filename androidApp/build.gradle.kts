plugins {
	kotlin("multiplatform")
	id("com.android.application")
	id("org.jetbrains.compose")
}

kotlin {
	android()
	sourceSets {
		val androidMain by getting {
			dependencies {
//				implementation(project(":shared"))
			}
		}
	}
}

android {
	compileSdk = 33
	defaultConfig {
		applicationId = "ru.nb.mdm"
		minSdk = 26
		versionCode = 1
		versionName = "1.0"
	}
	compileOptions {
		sourceCompatibility = JavaVersion.VERSION_17
		targetCompatibility = JavaVersion.VERSION_17
	}
}