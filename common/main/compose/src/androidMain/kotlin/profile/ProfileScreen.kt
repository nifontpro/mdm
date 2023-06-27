package profile

import androidx.compose.runtime.Composable
import com.adeo.kviewmodel.compose.observeAsState
import com.adeo.kviewmodel.odyssey.StoredViewModel
import navigation.NavigationTree
import profile.models.ProfileAction
import ru.alexgladkov.odyssey.compose.extensions.present
import ru.alexgladkov.odyssey.compose.local.LocalRootController

@Composable
fun ProfileScreen() {
	val rootController = LocalRootController.current

	StoredViewModel({ ProfileViewModel() }) { viewModel ->
		val viewState = viewModel.viewStates().observeAsState()
		val viewAction = viewModel.viewActions().observeAsState()

		ProfileView(
			viewState = viewState.value,
			eventHandler = viewModel::obtainEvent
		)

		when (viewAction.value) {
			is ProfileAction.Auth -> {
//				rootController.push(
//					screen = NavigationTree.Main.Profile.name
//				)
				rootController.findRootController().present(screen = NavigationTree.Auth.AuthFlow.name)
			}

			null -> {}
		}
	}
}