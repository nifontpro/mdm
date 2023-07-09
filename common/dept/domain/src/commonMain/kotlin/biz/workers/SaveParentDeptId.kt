package biz.workers

import biz.proc.ContextState
import biz.proc.DeptContext
import biz.proc.saveSettingsError
import ru.md.cor.ICorChainDsl
import ru.md.cor.worker

fun ICorChainDsl<DeptContext>.saveParentDeptId(title: String) = worker {
	this.title = title
	on { state == ContextState.RUNNING }

	handle {
		currentSettings.saveCurrentDeptId(deptId = currentDeptId)
	}

	except {
		saveSettingsError()
	}
}
