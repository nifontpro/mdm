package dept

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import dept.models.DeptEvent
import dept.models.DeptViewState
import theme.Theme

@Composable
fun DeptView(viewState: DeptViewState, eventHandler: (DeptEvent) -> Unit) {
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
				eventHandler.invoke(DeptEvent.AuthClicked)
			}) {
				Text("Logout")
			}
		}
	}
}
