package navigation.tabs

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import ru.alexgladkov.odyssey.compose.navigation.bottom_bar_navigation.TabConfiguration
import ru.alexgladkov.odyssey.compose.navigation.bottom_bar_navigation.TabItem
import ru.nb.mdm.main.compose.R
import theme.Theme

class DeptTab : TabItem() {
	override val configuration: TabConfiguration
		@Composable
		get() {
			return TabConfiguration(
				title = "Отделы",
				selectedIcon = painterResource(R.drawable.ic_action_name),
//				unselectedIcon = painterResource(R.drawable.user_hands),
				unselectedIcon = painterResource(R.drawable.ic_action_name),
				selectedColor = Theme.colors.primaryAction,
				unselectedColor = Theme.colors.hintTextColor,
				titleStyle = TextStyle(
					fontSize = 14.sp,
					fontWeight = FontWeight.Medium
				)
			)
		}
}

class UserTab : TabItem() {
	override val configuration: TabConfiguration
		@Composable
		get() {
			return TabConfiguration(
				title = "Сотрудники",
				selectedColor = Theme.colors.primaryAction,
				unselectedColor = Theme.colors.hintTextColor,
				titleStyle = TextStyle(
					fontSize = 14.sp,
					fontWeight = FontWeight.Medium
				)
			)
		}
}

class EventTab : TabItem() {
	override val configuration: TabConfiguration
		@Composable
		get() {
			return TabConfiguration(
				title = "События",
				selectedColor = Theme.colors.primaryAction,
				unselectedColor = Theme.colors.hintTextColor,
				titleStyle = TextStyle(
					fontSize = 14.sp,
					fontWeight = FontWeight.Medium
				)
			)
		}
}

class ProfileTab : TabItem() {
	override val configuration: TabConfiguration
		@Composable
		get() {
			return TabConfiguration(
				title = "Профиль",
				selectedColor = Theme.colors.primaryAction,
				unselectedColor = Theme.colors.hintTextColor,
				titleStyle = TextStyle(
					fontSize = 14.sp,
					fontWeight = FontWeight.Medium
				)
			)
		}
}
