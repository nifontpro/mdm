import org.gradle.api.JavaVersion

plugins {
	id("com.android.library")
}

android {
	compileSdk = 33

	defaultConfig {
		minSdk = 24
	}

	compileOptions {
		sourceCompatibility = JavaVersion.VERSION_17
		targetCompatibility = JavaVersion.VERSION_17
	}

	sourceSets {
		named("main") {
			manifest.srcFile("src/androidMain/AndroidManifest.xml")
			res.srcDirs("src/androidMain/res", "src/commonMain/resources")
		}
	}
}