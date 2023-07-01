package curent.repo

interface CurrentSettings {
	fun saveDeptParentId(deptId: Long)
	fun getDeptParentId(): Long
}