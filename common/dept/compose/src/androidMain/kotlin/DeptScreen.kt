import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import com.adeo.kviewmodel.compose.observeAsState
import com.adeo.kviewmodel.odyssey.StoredViewModel
import models.DeptEvent
import vm.DeptViewModel

@Composable
fun DeptScreen() {
//	val rootController = LocalRootController.current

	StoredViewModel({ DeptViewModel() }) { viewModel ->
		val viewState = viewModel.viewStates().observeAsState()
		val viewAction = viewModel.viewActions().observeAsState()
		val eventHandler = remember { viewModel::obtainEvent }

		OnLifecycleEvent { _, event ->
			println("Dept: $event")
			when (event) {
				Lifecycle.Event.ON_RESUME -> {
					eventHandler(DeptEvent.OnResume)
				}

				else -> {}
			}
		}

		DeptView(
			viewState = viewState.value,
			eventHandler = eventHandler
		)

		when (viewAction.value) {
			null -> {}
			else -> {}
		}
	}
}

@Composable
fun OnLifecycleEvent(onEvent: (owner: LifecycleOwner, event: Lifecycle.Event) -> Unit) {
	val eventHandler = rememberUpdatedState(onEvent)
	val lifecycleOwner = rememberUpdatedState(LocalLifecycleOwner.current)

	DisposableEffect(lifecycleOwner.value) {
		val lifecycle = lifecycleOwner.value.lifecycle
		val observer = LifecycleEventObserver { owner, event ->
			eventHandler.value(owner, event)
		}

		lifecycle.addObserver(observer)
		onDispose {
			lifecycle.removeObserver(observer)
		}
	}
}