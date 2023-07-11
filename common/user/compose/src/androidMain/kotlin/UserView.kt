import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
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
import model.UserEvent
import model.UserViewState
import theme.Theme
import utils.CheckNextItemsLoad

@Composable
fun UserView(paddingValues: PaddingValues, viewState: UserViewState, eventHandler: (UserEvent) -> Unit) {
	if (viewState.success) {
		Column(
			modifier = Modifier.padding(paddingValues)
		) {
			LazyColumn(
				modifier = Modifier
					.fillMaxWidth()
			) {
				val users = viewState.users
				items(users.size) { i ->
					val user = viewState.users[i]
					CheckNextItemsLoad(
						pageInfo = viewState.pageInfo,
						size = users.size,
						i = i,
						isLoading = viewState.isLoading
					) {
						eventHandler(UserEvent.OnLoadNextPage)
					}
					UserItem(user = user, eventHandler = eventHandler)
				}
			}
		}
	} else {
		LazyColumn(
			modifier = Modifier
				.padding(paddingValues)
				.fillMaxWidth()
		) {
			items(items = viewState.errors) { error ->
				Text(text = error, color = Color.Red)
			}
		}
	}
}

@Composable
private fun UserItem(user: User, eventHandler: (UserEvent) -> Unit) {
	Row(
		modifier = Modifier
			.fillMaxWidth()
			.padding(4.dp)
			.clickable { eventHandler(UserEvent.ClickUser(user.id)) },
		verticalAlignment = Alignment.CenterVertically,
		horizontalArrangement = Arrangement.SpaceBetween
	) {
		AsyncImage(
			modifier = Modifier
				.clip(RoundedCornerShape(50))
				.size(48.dp),
			model = user.mainImg,
			contentScale = ContentScale.FillBounds,
			contentDescription = "dept"
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

//			Text(
//				modifier = Modifier.padding(top = 4.dp),
//				text = user.classname.orEmpty(),
//				fontWeight = FontWeight.Normal,
//				fontSize = 14.sp,
//				color = Color.LightGray
//			)
		}
	}
}
