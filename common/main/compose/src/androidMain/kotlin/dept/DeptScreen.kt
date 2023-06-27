package dept

import androidx.compose.runtime.Composable
import com.adeo.kviewmodel.compose.observeAsState
import com.adeo.kviewmodel.odyssey.StoredViewModel
import navigation.NavigationTree
import dept.models.DeptAction
import ru.alexgladkov.odyssey.compose.extensions.present
import ru.alexgladkov.odyssey.compose.local.LocalRootController

@Composable
fun DeptScreen() {
	val rootController = LocalRootController.current

	StoredViewModel({ DeptViewModel() }) { viewModel ->
		val viewState = viewModel.viewStates().observeAsState()
		val viewAction = viewModel.viewActions().observeAsState()

		DeptView(viewState.value) { event ->
			viewModel.obtainEvent(event)
		}

		when (viewAction.value) {
			is DeptAction.Auth -> {
//				rootController.push(
//					screen = NavigationTree.Main.Profile.name
//				)
				rootController.findRootController().present(screen = NavigationTree.Auth.AuthFlow.name)
			}

			null -> {}
		}
	}
}