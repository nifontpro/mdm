import auth.repo.AuthSettings
import com.adeo.kviewmodel.BaseSharedViewModel
import curent.repo.CurrentSettings
import di.Inject
import kotlinx.coroutines.launch
import model.request.GetAuthParentIdRequest
import model.request.GetDeptsByParentIdRequest
import models.DeptAction
import models.DeptEvent
import models.DeptViewState
import repo.AuthRepository
import repo.DeptRepository

class DeptViewModel : BaseSharedViewModel<DeptViewState, DeptAction, DeptEvent>(
	initialState = DeptViewState(
		depts = emptyList(),
		deptParentId = 0,
	)
) {
	private val authRepository: AuthRepository = Inject.instance()
	private val deptRepository: DeptRepository = Inject.instance()
	private val authSettings: AuthSettings = Inject.instance()
	private val currentSettings: CurrentSettings = Inject.instance()

	init {
		getSettings()
	}

	override fun obtainEvent(viewEvent: DeptEvent) {
		when (viewEvent) {
			DeptEvent.AuthClicked -> auth()
		}
	}

	private fun auth() {

	}

	private fun getSettings() {
		viewModelScope.launch {
			val authId = authSettings.getAuthId()
			if (authId == 0L) return@launch
			var deptParentId = currentSettings.getDeptParentId()
			if (deptParentId == 0L) {
				val response = authRepository.getAuthParentId(GetAuthParentIdRequest(authId))
				if (response.success) {
					deptParentId = response.data ?: 0
					viewState = viewState.copy(deptParentId = deptParentId)
					currentSettings.saveDeptParentId(deptParentId)
				}
			} else {
				viewState = viewState.copy(deptParentId = deptParentId)
			}

			val response = deptRepository.getDeptByParentId(
				GetDeptsByParentIdRequest(
					authId = authId,
					parentId = deptParentId
				)
			)
			if (response.success) {
				viewState = viewState.copy(depts = response.data ?: emptyList())
			}
		}
	}
}