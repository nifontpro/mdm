package model.image

data class BaseImage(
	override val id: Long? = null,
	override val imageUrl: String = "",
	override val imageKey: String = "",
	override val miniUrl: String? = null,
	override val miniKey: String? = null,
	override val type: ImageType = ImageType.UNDEF,
	override val main: Boolean = false,
	override val createdAt: Long? = null,
) : IBaseImage
