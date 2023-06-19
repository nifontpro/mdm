package oauth

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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

		Button(onClick = {  }) {
			Text(text = "Logout...")
		}
	}
}

