package biz.proc

import biz.helper.ContextError
import model.response.PageInfo

abstract class BaseContext(
	var state: ContextState = ContextState.NONE,
	open var command: IBaseCommand = BaseCommand.NONE,
	val errors: MutableList<ContextError> = mutableListOf(),

	open var authId: Long = 0,

	var authEmail: String = "",
	open var pageInfo: PageInfo = PageInfo(),
//	var baseQuery: BaseQuery = BaseQuery(),
	var orderFields: List<String> = emptyList(), // Допустимые поля для сортировки
)

interface IBaseCommand

enum class BaseCommand : IBaseCommand {
	NONE
}

enum class ContextState {
	NONE,
	STARTING, // Старт процессора
	RUNNING,  // Старт операции
	FAILING,
	FINISHING,
}