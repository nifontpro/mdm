package dept

import com.adeo.kviewmodel.BaseSharedViewModel
import dept.models.DeptAction
import dept.models.DeptEvent
import dept.models.DeptViewState
import di.Inject
import kotlinx.coroutines.launch
import settings.AuthRepository

class DeptViewModel : BaseSharedViewModel<DeptViewState, DeptAction, DeptEvent>(
	initialState = DeptViewState(
		username = "Nifont Bus",
		avatarUrl = "https://static01.nyt.com/images/2022/09/16/arts/16CAMERON2/merlin_32238772_5ba78554-6e17-4091-b4cb-5b78d64086de-mobileMasterAt3x.jpg"
	)
) {
	private val authRepository: AuthRepository = Inject.instance()

	init {
		checkUserLoggedIn()
	}

	override fun obtainEvent(viewEvent: DeptEvent) {
		when (viewEvent) {
			DeptEvent.AuthClicked -> auth()
		}
	}

	private fun auth() {
		viewAction = DeptAction.Auth
	}

	private fun checkUserLoggedIn() {
		viewModelScope.launch {
			if (!authRepository.isUserLoggedIn()) {
				viewAction = DeptAction.Auth
			}
		}
	}
}