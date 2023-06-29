plugins {
	id("com.android.library")
	kotlin("multiplatform")
	id("org.jetbrains.compose")
}

kotlin {
	jvm("desktop")
	android()
	iosX64()
	iosArm64()
	iosSimulatorArm64()

	sourceSets {
		named("commonMain") {
			dependencies {
				implementation(compose.runtime)
				implementation(compose.foundation)
				implementation(compose.material)
			}
		}

		named("desktopMain") {
			dependencies {
				implementation(compose.desktop.common)
			}
		}

		named("androidMain") {
			dependencies {
				implementation(Dependencies.Android.Compose.ui)
				implementation(Dependencies.Android.Compose.material)
				implementation(Dependencies.Android.Compose.tooling)
				implementation(Dependencies.Android.Compose.icons)
			}
		}
	}

	tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
		kotlinOptions.jvmTarget = "17"
	}
}

android {
	buildFeatures {
		compose = true
	}

	defaultConfig {
		manifestPlaceholders["appAuthRedirectScheme"] = "ru.nb.mdm"
	}
}