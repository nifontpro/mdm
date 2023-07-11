import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.Lifecycle
import com.adeo.kviewmodel.compose.observeAsState
import com.adeo.kviewmodel.odyssey.StoredViewModel
import kotlinx.coroutines.launch
import models.DeptAction
import models.DeptEvent
import utils.OnLifecycleEvent
import vm.DeptViewModel

@Composable
fun DeptScreen() {
//	val rootController = LocalRootController.current

	StoredViewModel({ DeptViewModel() }) { viewModel ->
		val viewState by viewModel.viewStates().observeAsState()
		val viewAction = viewModel.viewActions().observeAsState()
		val eventHandler = remember { viewModel::obtainEvent }

		OnLifecycleEvent { _, event ->
			when (event) {
				Lifecycle.Event.ON_RESUME -> {
					eventHandler(DeptEvent.OnResume)
				}

				else -> {}
			}
		}

		val scaffoldState = rememberScaffoldState()
		val coroutineScope = rememberCoroutineScope()
		val context = LocalContext.current
		LaunchedEffect(viewState.errors) {
			val errors = viewState.errors
			if (errors.isNotEmpty() && viewState.success) {
				errors.forEach {
//					Toast.makeText(context, it, Toast.LENGTH_LONG).show()
					scaffoldState.snackbarHostState.showSnackbar(it)
				}
			}
		}

		Scaffold(
			backgroundColor = Color.Black,
			modifier = Modifier.fillMaxSize(),
			scaffoldState = scaffoldState
		) {
			DeptView(
				paddingValues = it,
				viewState = viewState,
				eventHandler = eventHandler
			)
		}

		when (viewAction.value) {
			null -> {}

			is DeptAction.Test -> {
				coroutineScope.launch {
					scaffoldState.snackbarHostState.showSnackbar(
						(viewAction.value as DeptAction.Test).message
					)
				}
				eventHandler(DeptEvent.Clear)
			}
		}
	}
}