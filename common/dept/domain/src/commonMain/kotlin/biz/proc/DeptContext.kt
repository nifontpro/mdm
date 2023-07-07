package biz.proc

import auth.repo.AuthSettings
import di.Inject
import model.Dept
import repo.DeptRepository

class DeptContext(
	override var command: IBaseCommand,
	override var authId: Long = 0,
	var dept: Dept = Dept(),
	var depts: List<Dept> = emptyList(),
	var selectDeptId: Long = 0,
	var parentId: Long = 0,
) : BaseContext() {
	val deptRepository: DeptRepository = Inject.instance()
	val authSettings: AuthSettings = Inject.instance()

	companion object {
		const val REPO = "Dept"
	}

}

enum class DeptCommand : IBaseCommand {
	GET_SETTINGS,
}
