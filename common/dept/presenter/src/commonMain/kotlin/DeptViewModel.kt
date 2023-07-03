import auth.repo.AuthSettings
import com.adeo.kviewmodel.BaseSharedViewModel
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
		authId = 0,
		depts = emptyList(),
		currentDeptId = 0,
	)
) {
	private val authRepository: AuthRepository = Inject.instance()
	private val deptRepository: DeptRepository = Inject.instance()
	private val authSettings: AuthSettings = Inject.instance()

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
			if (authId == 0L || authId == viewState.authId) return@launch
			viewState = viewState.copy(authId = authId)

			val deptIdResponse = authRepository.getAuthDeptId(GetAuthDeptIdRequest(authId))
			val deptId = deptIdResponse.data
			if (deptIdResponse.success && deptId != null && deptId != viewState.currentDeptId) {
				viewState = viewState.copy(currentDeptId = deptId)
			} else {
				return@launch
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

	private fun onResume() {
		getSettings()
	}

}