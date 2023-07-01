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
import model.Dept
import models.DeptEvent
import models.DeptViewState
import theme.Theme

@Composable
fun DeptView(viewState: DeptViewState, eventHandler: (DeptEvent) -> Unit) {
	LazyColumn(
		modifier = Modifier
			.fillMaxWidth()
	) {
		items(items = viewState.depts) { dept ->
			DeptItem(dept, eventHandler)
		}
	}
}

@Composable
private fun DeptItem(dept: Dept, eventHandler: (DeptEvent) -> Unit) {
	Row(
		modifier = Modifier
			.fillMaxWidth()
			.padding(4.dp),
		verticalAlignment = Alignment.CenterVertically,
		horizontalArrangement = Arrangement.SpaceBetween
	) {
		AsyncImage(
			modifier = Modifier
				.clip(RoundedCornerShape(50))
				.size(48.dp),
			model = dept.mainImg,
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
				text = dept.name,
				fontWeight = FontWeight.Bold,
				fontSize = 16.sp,
				color = Theme.colors.secondaryTextColor
			)

			Text(
				modifier = Modifier.padding(top = 4.dp),
				text = dept.classname.orEmpty(),
				fontWeight = FontWeight.Normal,
				fontSize = 14.sp,
				color = Color.LightGray
			)
		}
	}
}
