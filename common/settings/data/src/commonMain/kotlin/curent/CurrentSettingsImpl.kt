package curent

import com.russhwolf.settings.Settings
import com.russhwolf.settings.get
import curent.repo.CurrentSettings

class CurrentSettingsImpl(
	private val settings: Settings
) : CurrentSettings {

	override fun saveCurrentDeptId(deptId: Long) {
		settings.putLong(CURRENT_DEPT_ID, deptId)
	}

	override fun getCurrentDeptId(): Long {
		return settings[CURRENT_DEPT_ID, 0L]
	}

	override fun removeCurrentDeptId() {
		return settings.remove(CURRENT_DEPT_ID)
	}

	override fun saveParentDeptId(deptId: Long) {
		settings.putLong(PARENT_DEPT_ID, deptId)
	}

	override fun getParentDeptId(): Long {
		return settings[PARENT_DEPT_ID, 0L]
	}

	override fun removeParentDeptId() {
		return settings.remove(PARENT_DEPT_ID)
	}

	override fun saveAuthId(authId: Long) {
		settings.putLong(AUTH_ID, authId)
	}

	override fun getAuthId(): Long {
		return settings[AUTH_ID, 0L]
	}

	companion object {
		private const val CURRENT_DEPT_ID = "current_dept_id"
		private const val PARENT_DEPT_ID = "parent_dept_id"
		const val AUTH_ID = "auth_id"
	}
}