@file:Suppress("unused")

package biz.helper

import biz.proc.BaseContext
import biz.proc.ContextState
import model.response.BaseResponse

fun BaseContext.addError(error: ContextError) = errors.add(error)

fun BaseContext.fail(error: ContextError) {
	addError(error)
	state = ContextState.FAILING
}

suspend fun <T> BaseContext.checkResponse(operation: suspend () -> BaseResponse<T>): T? {
	val result = operation()
	return if (result.success) {
		pageInfo = result.pageInfo
		result.data
	} else {
		errors.addAll(result.errors)
		state = ContextState.FAILING
		null
	}
}

fun errorValidation(
	field: String,
	/**
	 * Код, характеризующий ошибку. Не должен включать имя поля или указание на валидацию.
	 * Например: empty, badSymbols, tooLong, etc
	 */
	violationCode: String,
	description: String,
	level: ContextError.Levels = ContextError.Levels.INFO,
) = ContextError(
	code = "validation-$field:$violationCode",
	field = field,
	group = "validation",
	message = description,
	level = level,
)

fun errorDb(
	repository: String,
	violationCode: String,
	description: String,
	level: ContextError.Levels = ContextError.Levels.ERROR,
) = ContextError(
	code = "db-$repository:$violationCode",
	field = repository,
	group = "db",
	message = "БД: $description",
	level = level,
)

fun restError(
	repository: String,
	violationCode: String,
	description: String,
	level: ContextError.Levels = ContextError.Levels.ERROR,
) = ContextError(
	code = "rest-$repository:$violationCode",
	field = repository,
	group = "rest",
	message = description,
	level = level,
)

fun errorUnauthorized(
	role: String = "not found", // Уровень доступа
	message: String,
	level: ContextError.Levels = ContextError.Levels.UNAUTHORIZED,
) = ContextError(
	code = "unauthorized-$role",
	field = role,
	group = "unauthorized",
	message = message,
	level = level,
)

fun otherError(
	description: String,
	field: String,
	code: String = "other",
	level: ContextError.Levels = ContextError.Levels.INFO,
) = ContextError(
	code = code,
	field = field,
	group = "other",
	message = description,
	level = level,
)