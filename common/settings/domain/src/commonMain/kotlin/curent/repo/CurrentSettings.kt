package curent.repo

interface CurrentSettings {
	fun saveCurrentDeptId(deptId: Long)
	fun getCurrentDeptId(): Long
	fun removeCurrentDeptId()
}