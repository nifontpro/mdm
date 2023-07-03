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

	companion object {
		private const val CURRENT_DEPT_ID = "current_dept_id"
	}
}