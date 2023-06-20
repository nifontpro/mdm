package oauth

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import oauth.vm.OAuthEvent
import oauth.vm.OAuthViewState

@Composable
fun OAuthView(
	state: OAuthViewState,
	eventHandler: (OAuthEvent) -> Unit
) {

	Column {

		Button(onClick = { eventHandler(OAuthEvent.LoginClick) }) {
			Text(text = "Login")
		}

		Button(onClick = { eventHandler(OAuthEvent.LogoutClick) }) {
			Text(text = "Logout...")
		}

		Button(onClick = { eventHandler(OAuthEvent.ShowTokensClick) }) {
			Text(text = "Show tokens")
		}

		Button(onClick = { eventHandler(OAuthEvent.ProfilesClick) }) {
			Text(text = "Profiles")
		}

		if (state.isAuth) {
			Text(text = "Вход выполнен успешно", color = Color.Cyan)
		} else {
			Text(text = "Вход не выполнен", color = Color.Red)
		}
	}
}

