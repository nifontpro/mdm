import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.Lifecycle
import com.adeo.kviewmodel.compose.observeAsState
import com.adeo.kviewmodel.odyssey.StoredViewModel
import model.EventEvent
import utils.OnLifecycleEvent
import vm.EventViewModel

@Composable
fun EventScreen() {
//	val rootController = LocalRootController.current
	StoredViewModel({ EventViewModel() }) { viewModel ->
		val viewState by viewModel.viewStates().observeAsState()
		val viewAction = viewModel.viewActions().observeAsState()
		val eventHandler = remember { viewModel::obtainEvent }

		OnLifecycleEvent { _, event ->
			when (event) {
				Lifecycle.Event.ON_RESUME -> {
					println("Event: ON_RESUME")
					eventHandler(EventEvent.OnResume)
				}

				else -> {}
			}
		}

		val scaffoldState = rememberScaffoldState()

		LaunchedEffect(viewState.errors) {
			val errors = viewState.errors
			if (errors.isNotEmpty() && viewState.success) {
				errors.forEach {
					scaffoldState.snackbarHostState.showSnackbar(it)
				}
			}
		}

		Scaffold(
			backgroundColor = Color.Black,
			modifier = Modifier.fillMaxSize(),
			scaffoldState = scaffoldState
		) {
			EventView(
				paddingValues = it,
				viewState = viewState,
				eventHandler = eventHandler
			)
		}

		when (viewAction.value) {
			null -> {}
			else -> {}
		}
	}
}