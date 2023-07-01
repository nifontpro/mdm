package model

import kotlinx.serialization.Serializable
import model.image.BaseImage

@Serializable
data class Dept(
	val id: Long = 0,
	val parentId: Long = 0,
	val name: String = "",
	val classname: String? = null,
	val topLevel: Boolean = false,
	val mainImg: String? = null,
	val type: DeptType = DeptType.UNDEF,
	val images: List<BaseImage> = emptyList(),
)