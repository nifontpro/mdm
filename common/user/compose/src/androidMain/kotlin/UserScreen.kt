import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.Lifecycle
import com.adeo.kviewmodel.compose.observeAsState
import com.adeo.kviewmodel.odyssey.StoredViewModel
import kotlinx.coroutines.launch
import model.UserAction
import model.UserEvent
import utils.OnLifecycleEvent
import vm.UserViewModel

@Composable
fun UserScreen() {
//	val rootController = LocalRootController.current

	StoredViewModel({ UserViewModel() }) { viewModel ->
		val viewState by viewModel.viewStates().observeAsState()
		val viewAction = viewModel.viewActions().observeAsState()
		val eventHandler = remember { viewModel::obtainEvent }

		OnLifecycleEvent { _, event ->
			when (event) {
				Lifecycle.Event.ON_RESUME -> {
					println("User: ON_RESUME")
					eventHandler(UserEvent.OnResume)
				}

				else -> {}
			}
		}

		val scaffoldState = rememberScaffoldState()
		val coroutineScope = rememberCoroutineScope()

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
			UserView(
				paddingValues = it,
				viewState = viewState,
				eventHandler = eventHandler
			)
		}

		when (viewAction.value) {
			null -> {}

			is UserAction.Test -> {
				coroutineScope.launch {
					scaffoldState.snackbarHostState.showSnackbar(
						(viewAction.value as UserAction.Test).message
					)
				}
				eventHandler(UserEvent.Clear)
			}
		}
	}
}