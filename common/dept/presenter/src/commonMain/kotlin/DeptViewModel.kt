import auth.repo.AuthSettings
import com.adeo.kviewmodel.BaseSharedViewModel
import curent.repo.CurrentSettings
import di.Inject
import kotlinx.coroutines.launch
import model.request.GetAuthDeptIdRequest
import model.request.GetCurrentDeptsRequest
import models.DeptAction
import models.DeptEvent
import models.DeptViewState
import repo.AuthRepository
import repo.DeptRepository

class DeptViewModel : BaseSharedViewModel<DeptViewState, DeptAction, DeptEvent>(
	initialState = DeptViewState(
		depts = emptyList(),
		currentDeptId = 0,
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
			DeptEvent.OnResume -> onResume()
		}
	}

	private fun getSettings() {
		viewModelScope.launch {
			val authId = authSettings.getAuthId()
			if (authId == 0L) return@launch
			var deptId = currentSettings.getCurrentDeptId()
			if (deptId == 0L) {
				val response = authRepository.getAuthDeptId(GetAuthDeptIdRequest(authId))
				if (response.success) {
					deptId = response.data ?: 0
					viewState = viewState.copy(currentDeptId = deptId)
					currentSettings.saveCurrentDeptId(deptId)
				}
			} else {
				viewState = viewState.copy(currentDeptId = deptId)
			}

			val response = deptRepository.getCurrentDepts(
				GetCurrentDeptsRequest(
					authId = authId,
					deptId = deptId
				)
			)
			if (response.success) {
				viewState = viewState.copy(depts = response.data ?: emptyList())
			}
		}
	}

	private fun onResume(){
		getSettings()
	}

}