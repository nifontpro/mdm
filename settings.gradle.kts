pluginManagement {
	repositories {
		google()
		gradlePluginPortal()
		mavenCentral()
	}
}
rootProject.name = "mdm"

include(":androidApp")
include(":common")
include(":common:core")

include(":common:base-compose")
include(":common:base-core")
include(":common:base-ios")

include(":common:auth")