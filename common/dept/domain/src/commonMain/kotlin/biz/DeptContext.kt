package biz

import model.Dept
import model.biz.proc.BaseContext
import model.biz.proc.IBaseCommand

class DeptContext(
	override var command: IBaseCommand,
	var dept: Dept = Dept(),
	var depts: List<Dept> = emptyList(),
	var selectDeptId: Long = 0,
	var parentId: Long = 0,
) : BaseContext() {
	fun x() {
		command = DeptCommand.TEST
	}
}

enum class DeptCommand : IBaseCommand {
	TEST,
	GET_SETTINGS,
}
