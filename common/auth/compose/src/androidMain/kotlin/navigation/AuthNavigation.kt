package navigation

import androidx.compose.material.Text
import androidx.compose.ui.graphics.Color
import ru.alexgladkov.odyssey.compose.extensions.flow
import ru.alexgladkov.odyssey.compose.extensions.screen
import ru.alexgladkov.odyssey.compose.navigation.RootComposeBuilder

fun RootComposeBuilder.authFlow() {
	flow(name = NavigationTree.Auth.AuthFlow.name) {
		screen(name = NavigationTree.Auth.Login.name) {
			Text("Login Screen", color = Color.LightGray)
		}

		screen(name = NavigationTree.Auth.Register.name) {
			Text("RegisterScreen", color = Color.LightGray)
		}

		screen(name = NavigationTree.Auth.Forgot.name) {
			Text("Forgot Screen", color = Color.LightGray)
		}
	}
}