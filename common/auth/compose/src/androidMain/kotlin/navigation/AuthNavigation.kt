package navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import oauth.OAuthScreen
import ru.alexgladkov.odyssey.compose.extensions.flow
import ru.alexgladkov.odyssey.compose.extensions.present
import ru.alexgladkov.odyssey.compose.extensions.screen
import ru.alexgladkov.odyssey.compose.local.LocalRootController
import ru.alexgladkov.odyssey.compose.navigation.RootComposeBuilder

fun RootComposeBuilder.authFlow() {

	flow(name = NavigationTree.Auth.AuthFlow.name) {
		screen(name = NavigationTree.Auth.Login.name) {

			val rootController = LocalRootController.current

			Column(
				modifier = Modifier.fillMaxWidth(),
				horizontalAlignment = Alignment.CenterHorizontally
			) {
				Text("Login Screen", color = Color.LightGray)
				Button(onClick = {
					rootController.present(NavigationTree.Auth.OAuth.name)
				}) {
					Text("OAuth")
				}
			}
		}

		screen(name = NavigationTree.Auth.OAuth.name) {
			OAuthScreen()
		}

		screen(name = NavigationTree.Auth.Forgot.name) {
			Text("Forgot Screen", color = Color.LightGray)
		}
	}
}