package biz.proc

import biz.helper.ContextError
import curent.repo.CurrentSettings
import di.Inject
import model.response.PageInfo

abstract class BaseContext(
	var state: ContextState = ContextState.NONE,
	open var command: IBaseCommand = BaseCommand.NONE,

	val errors: MutableList<ContextError> = mutableListOf(),

	override var pageInfo: PageInfo = PageInfo(),
	override var isLoading: Boolean = false,
	override var authId: Long = 0,
	override var onStart: Boolean = true,

	open var deptId: Long = 0,
	var clearData: Boolean = false,

//	var authEmail: String = "",
//	var baseQuery: BaseQuery = BaseQuery(),
//	var orderFields: List<String> = emptyList(), // Допустимые поля для сортировки
) : BaseState {
	val currentSettings: CurrentSettings = Inject.instance()
}

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