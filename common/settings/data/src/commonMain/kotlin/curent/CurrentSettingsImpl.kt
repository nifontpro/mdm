package curent

import com.russhwolf.settings.Settings
import com.russhwolf.settings.get
import curent.repo.CurrentSettings

class CurrentSettingsImpl(
	private val settings: Settings
) : CurrentSettings {

	override fun saveDeptParentId(deptId: Long) {
		settings.putLong(DEPT_PARENT_ID, deptId)
	}

	override fun getDeptParentId(): Long {
		return settings[DEPT_PARENT_ID, 0L]
	}

	companion object {
		private const val DEPT_PARENT_ID = "dept_parent_id"
	}
}