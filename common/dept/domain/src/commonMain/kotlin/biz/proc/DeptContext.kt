package biz.proc

import di.Inject
import model.Dept
import repo.DeptRepository

class DeptContext(
	override var command: IBaseCommand,
	override var authId: Long = 0,
	var dept: Dept = Dept(),
	var depts: List<Dept> = emptyList(),

	override var onStart: Boolean = true,
	var currentDeptId: Long = 0,
	var parentDeptId: Long = 0,
	var clickDeptId: Long = 0,
) : BaseContext() {
	val deptRepository: DeptRepository = Inject.instance()

	companion object {
		const val REPO = "Dept"
	}

}

enum class DeptCommand : IBaseCommand {
	GET_SETTINGS,
	ON_TOP_LEVEL,
	TO_DEPT,
	CHANGE_CURRENT_DEPT,
}
