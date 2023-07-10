package profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CheckBox
import androidx.compose.material.icons.outlined.CheckBoxOutlineBlank
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import model.User
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
				ProfileItem(user, authId = viewState.authId, eventHandler)
			}
		}
	}
}

@Composable
private fun ProfileItem(user: User, authId: Long, eventHandler: (ProfileEvent) -> Unit) {
	Row(
		modifier = Modifier
			.fillMaxWidth()
			.background(if (user.id == authId) Color.Gray else Color.Transparent)
			.padding(4.dp),
		verticalAlignment = Alignment.CenterVertically,
		horizontalArrangement = Arrangement.SpaceBetween
	) {
		AsyncImage(
			modifier = Modifier
				.clip(RoundedCornerShape(50))
				.size(48.dp),
			model = user.mainImg,
			contentScale = ContentScale.FillBounds,
			contentDescription = "user"
		)

		Column(
			modifier = Modifier
				.padding(horizontal = 8.dp)
				.weight(1f)
		) {
			Text(
				modifier = Modifier.padding(top = 4.dp),
				text = user.getFIO(),
				fontWeight = FontWeight.Bold,
				fontSize = 16.sp,
				color = Theme.colors.secondaryTextColor
			)

			Text(
				modifier = Modifier.padding(top = 4.dp),
				text = user.post.orEmpty(),
				fontWeight = FontWeight.Normal,
				fontSize = 14.sp,
				color = Color.LightGray
			)
		}

		Icon(
			imageVector = if (user.id == authId) Icons.Outlined.CheckBox else Icons.Outlined.CheckBoxOutlineBlank,
			contentDescription = "Selected",
			tint = Color.LightGray,
			modifier = Modifier
				.size(20.dp)
				.clickable {
					eventHandler(ProfileEvent.AuthIdChanged(authId = user.id))
				}
		)
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
				.clip(RoundedCornerShape(50))
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
