class DesktopPlatform : Platform {
	override val name: String = "Desktop Platform"
}

actual fun getPlatform(): Platform = DesktopPlatform()