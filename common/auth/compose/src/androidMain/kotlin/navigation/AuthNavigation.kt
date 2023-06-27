package navigation

import oauth.OAuthScreen
import ru.alexgladkov.odyssey.compose.extensions.flow
import ru.alexgladkov.odyssey.compose.extensions.screen
import ru.alexgladkov.odyssey.compose.navigation.RootComposeBuilder

fun RootComposeBuilder.authFlow() {
	flow(name = NavigationTree.Auth.AuthFlow.name) {
		screen(name = NavigationTree.Auth.OAuth.name) {
			OAuthScreen()
		}
	}
}