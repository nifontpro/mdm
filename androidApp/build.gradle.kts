plugins {
	id("com.android.application")
	id("org.jetbrains.compose")
	kotlin("android")
}

android {
	compileSdk = 33
	defaultConfig {
		applicationId = "ru.nb.mdm"
		minSdk = 24
		versionCode = 1
		versionName = "1.0"

		@Suppress("UnstableApiUsage")
		manifestPlaceholders["appAuthRedirectScheme"] = "ru.nb.mdm"
	}

	@Suppress("UnstableApiUsage")
	buildFeatures {
		compose = true
	}

//	composeOptions {
//		kotlinCompilerExtensionVersion = "1.4.0"
//	}

	compileOptions {
		sourceCompatibility = JavaVersion.VERSION_17
		targetCompatibility = JavaVersion.VERSION_17
	}
}

dependencies {
	implementation(project(":common:core"))
	implementation(project(":common:base:compose"))
	implementation(project(":common:base-core"))

	implementation(Dependencies.Android.Compose.runtime)
	implementation(Dependencies.Android.Compose.ui)
	implementation(Dependencies.Android.Compose.material)
	implementation(Dependencies.Android.Compose.icons)
	implementation(Dependencies.Android.Compose.tooling)

	implementation("com.google.android.material:material:1.9.0")
	implementation("androidx.appcompat:appcompat:1.6.1")
}