package navigation.tabs

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import ru.alexgladkov.odyssey.compose.navigation.bottom_bar_navigation.TabConfiguration
import ru.alexgladkov.odyssey.compose.navigation.bottom_bar_navigation.TabItem
import theme.Theme

class DeptTab : TabItem() {
	override val configuration: TabConfiguration
		@Composable
		get() {
			return TabConfiguration(
				title = "Отделы",
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

class AwardTab : TabItem() {
	override val configuration: TabConfiguration
		@Composable
		get() {
			return TabConfiguration(
				title = "Награды",
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
