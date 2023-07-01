import androidx.compose.runtime.Composable
import com.adeo.kviewmodel.compose.observeAsState
import com.adeo.kviewmodel.odyssey.StoredViewModel
import ru.alexgladkov.odyssey.compose.local.LocalRootController

@Composable
fun DeptScreen() {
	val rootController = LocalRootController.current

	StoredViewModel({ DeptViewModel() }) { viewModel ->
		val viewState = viewModel.viewStates().observeAsState()
		val viewAction = viewModel.viewActions().observeAsState()

		DeptView(
			viewState = viewState.value,
			eventHandler = viewModel::obtainEvent
		)

		when (viewAction.value) {
			null -> {}
			else -> {}
		}
	}
}