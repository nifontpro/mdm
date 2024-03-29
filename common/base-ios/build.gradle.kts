 plugins {
	id("multiplatform-setup")
	id("android-setup")
	kotlin("native.cocoapods")
}

version = "0.0.1"

kotlin {
	cocoapods {
		summary = "Medalist iOS SDK"
		homepage = "https://nifontbus.ru"
		version = "1.0"
		ios.deploymentTarget = "16.0"
//		podfile = project.file("../../iosApp/Podfile")

		framework {
			transitiveExport = false
			baseName = "SharedSDK"
//			isStatic = true // по умолчанию false
			export(project(":common:core"))
			export(project(":common:base-core"))
			export(project(":common:core-utils"))
			export(project(":common:auth:domain"))
			export(project(":common:auth:domain"))
			export(project(":common:auth:presenter"))
//
//			binaryOption("bundledId", "ru.nb.mdm")
//			binaryOption("bundledVersion", "1")
		}
	}

	sourceSets {
		val commonMain by getting {
			dependencies {
				implementation(project(":common:core"))
				implementation(project(":common:base-core"))
				implementation(project(":common:core-utils"))
				implementation(project(":common:auth:domain"))
				implementation(project(":common:auth:presenter"))
				implementation(project(":common:dept:domain"))
				implementation(project(":common:dept:presenter"))
				implementation(project(":common:user:domain"))
				implementation(project(":common:user:presenter"))
				implementation(project(":common:event:domain"))
				implementation(project(":common:event:presenter"))
			}
		}

		val iosX64Main by getting
		val iosArm64Main by getting
		val iosSimulatorArm64Main by getting

		val iosMain by creating {
			dependencies {
				api(project(":common:core"))
				api(project(":common:base-core"))
				api(project(":common:core-utils"))

				api(project(":common:auth:domain"))
				api(project(":common:auth:presenter"))
			}
			dependsOn(commonMain)
			iosX64Main.dependsOn(this)
			iosArm64Main.dependsOn(this)
			iosSimulatorArm64Main.dependsOn(this)
		}

		listOf(
			iosX64(),
			iosArm64(),
			iosSimulatorArm64()
		).forEach {
			it.binaries.framework {
				baseName = "common"
				binaryOption("bundleId", "ru.nb.mdm.common.base-ios")
			}
		}
	}
}