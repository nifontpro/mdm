import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import com.adeo.kviewmodel.compose.observeAsState
import com.adeo.kviewmodel.odyssey.StoredViewModel
import ru.alexgladkov.odyssey.compose.local.LocalRootController

@Composable
fun DeptScreen() {
	val rootController = LocalRootController.current

	OnLifecycleEvent { owner, event ->
		println("DeptScreen $event, $owner")
		when (event) {
			Lifecycle.Event.ON_RESUME -> {

			}
			else -> {  }
		}
	}

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