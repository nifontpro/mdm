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
include(":common:core-utils")
include(":common:core-compose")

include(":common:base")
include(":common:base:domain")
include(":common:base:compose")

include(":common:base-core")
include(":common:base-ios")

include(":common:auth")
include(":common:auth:domain")
include(":common:auth:data")
include(":common:auth:presenter")
include(":common:auth:compose")

include(":common:main")
include(":common:main:domain")
//include(":common:main:data")
include(":common:main:presenter")
include(":common:main:compose")