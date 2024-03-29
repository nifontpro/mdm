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
import model.Dept
import models.DeptEvent
import models.DeptViewState
import theme.Theme

@Composable
fun DeptView(paddingValues: PaddingValues, viewState: DeptViewState, eventHandler: (DeptEvent) -> Unit) {
	if (viewState.success) {
		Column(
			modifier = Modifier.padding(paddingValues)
		) {
			Row {
				Button(onClick = { eventHandler(DeptEvent.OnTopLevel) }) {
					Text("Top")
				}
				Button(onClick = { eventHandler(DeptEvent.OnTest("Test data")) }) {
					Text("Test")
				}
			}
			LazyColumn(
				modifier = Modifier
					.fillMaxWidth()
			) {
				items(items = viewState.depts) { dept ->
					DeptItem(dept = dept, currentDeptId = viewState.currentDeptId, eventHandler = eventHandler)
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
private fun DeptItem(dept: Dept, currentDeptId: Long, eventHandler: (DeptEvent) -> Unit) {
	Row(
		modifier = Modifier
			.fillMaxWidth()
			.background(if (dept.id == currentDeptId) Color.Gray else Color.Transparent)
			.padding(4.dp)
			.clickable { eventHandler(DeptEvent.ClickDept(dept.id)) },
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
		Icon(
			imageVector = if (dept.id == currentDeptId) Icons.Outlined.CheckBox else Icons.Outlined.CheckBoxOutlineBlank,
			contentDescription = "Selected",
			tint = Color.LightGray,
			modifier = Modifier
				.size(20.dp)
				.clickable {
					eventHandler(DeptEvent.CurrentDeptIdChanged(currentDeptId = dept.id))
				}
		)
	}
}
