package profile

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import profile.models.ProfileEvent
import profile.models.ProfileViewState
import theme.Theme

@Composable
fun ProfileView(viewState: ProfileViewState, eventHandler: (ProfileEvent) -> Unit) {
	Column {
		ProfileHeader(viewState, eventHandler)
		LazyColumn(
			modifier = Modifier
				.fillMaxWidth()
				.weight(1f)
		) {
			items(items = viewState.profiles) { user ->
				Text(text = user.firstname, color = Color.LightGray)
			}
		}
	}

}

@Composable
private fun ProfileHeader(
	viewState: ProfileViewState,
	eventHandler: (ProfileEvent) -> Unit
) {
	Row(
		modifier = Modifier
			.clickable {

			}
			.padding(start = 24.dp, end = 24.dp, top = 26.dp)
	) {
		AsyncImage(
			modifier = Modifier
				.clip(RoundedCornerShape(28.dp))
				.size(56.dp),
			model = viewState.avatarUrl,
			contentScale = ContentScale.FillBounds,
			contentDescription = "Avatar"
		)

		Column(modifier = Modifier.padding(start = 20.dp)) {
			Text(
				modifier = Modifier.padding(top = 4.dp),
				text = viewState.username,
				fontWeight = FontWeight.Bold,
				fontSize = 22.sp,
				color = Theme.colors.secondaryTextColor
			)

			Button(onClick = {
				eventHandler(ProfileEvent.AuthClicked)
			}) {
				Text("Авторизация")
			}
		}
	}
}
