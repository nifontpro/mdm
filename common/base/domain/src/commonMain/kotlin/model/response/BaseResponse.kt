package model.response

import kotlinx.serialization.Serializable
import model.biz.helper.ContextError

@Serializable
data class BaseResponse<out T>(
	val data: T? = null,
	val success: Boolean = true,
	val errors: List<ContextError> = emptyList(),
	val pageInfo: PageInfo? = null,
) {
	companion object {
		fun <T> success(data: T? = null, pageInfo: PageInfo? = null) =
			BaseResponse(data = data, success = true, pageInfo = pageInfo)

		fun error(errors: List<ContextError>) = BaseResponse(data = null, success = false, errors = errors)
	}
}