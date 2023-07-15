package login

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import models.AuthEvent
import models.AuthViewState

@Composable
fun AuthView(
	state: AuthViewState,
	eventHandler: (AuthEvent) -> Unit
) {

	if (state.isLoading) {
		Box(modifier = Modifier.fillMaxSize()) {
			CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
		}
	} else {
		Column {

			Button(
				onClick = { eventHandler(AuthEvent.MainFlowClick) },
				enabled = state.isAuth
			) {
				Text(text = "Main menu")
			}

			Button(onClick = { eventHandler(AuthEvent.LoginClick) }) {
				Text(text = "Login")
			}

			Button(onClick = { eventHandler(AuthEvent.LogoutClick) }) {
				Text(text = "Logout...")
			}

			if (state.isAuth) {
				Text(text = "Вход выполнен успешно", color = Color.Cyan)
			} else {
				Text(text = "Вход не выполнен", color = Color.Red)
			}
		}
	}
}

