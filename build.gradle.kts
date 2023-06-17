buildscript {
	repositories {
		gradlePluginPortal()
		google()
		mavenCentral()
	}
	dependencies {
		classpath(Dependencies.Kotlin.gradlePlugin)
		classpath("com.android.tools.build:gradle:7.4.2")
		classpath(Dependencies.SqlDelight.gradlePlugin)
	}
}

allprojects {
	repositories {
		google()
		mavenCentral()
	}
}

tasks.register("clean", Delete::class) {
	delete(rootProject.buildDir)
}
