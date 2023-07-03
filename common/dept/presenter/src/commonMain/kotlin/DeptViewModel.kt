import auth.repo.AuthSettings
import com.adeo.kviewmodel.BaseSharedViewModel
import di.Inject
import kotlinx.coroutines.launch
import model.request.GetAuthDeptRequest
import model.request.GetCurrentDeptsRequest
import models.DeptAction
import models.DeptEvent
import models.DeptViewState
import repo.DeptRepository

class DeptViewModel : BaseSharedViewModel<DeptViewState, DeptAction, DeptEvent>(
	initialState = DeptViewState()
) {

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

			if (authId == 0L) {
				viewState = viewState.copy(
					success = false,
					errors = listOf("Необходимо авторизоваться или выбрать профиль"),
					authId = 0,
				)
				return@launch
			}

			if (authId == viewState.authId) {
				return@launch
			}

			val deptResponse = deptRepository.getAuthDept(GetAuthDeptRequest(authId))
			val dept = deptResponse.data
			if (!deptResponse.success || dept == null) {
				viewState = viewState.copy(
					success = false,
					errors = deptResponse.errors.map { it.message },
					selectDeptId = 0,
				)
				return@launch
			}

			val response = deptRepository.getCurrentDepts(
				GetCurrentDeptsRequest(
					authId = authId,
					parentId = dept.parentId
				)
			)
			if (response.success) {
				viewState = viewState.copy(
					success = true,
					authId = authId,
					selectDeptId = dept.id,
					parentId = dept.parentId,
					depts = response.data ?: emptyList(),
				)
			}
		}
	}

	private fun onResume() {
		getSettings()
	}

}