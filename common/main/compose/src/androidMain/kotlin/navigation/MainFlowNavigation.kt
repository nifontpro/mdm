package navigation

import DeptScreen
import UserScreen
import navigation.tabs.*
import navigation.test.TestScreen
import profile.ProfileScreen
import ru.alexgladkov.odyssey.compose.extensions.bottomNavigation
import ru.alexgladkov.odyssey.compose.extensions.screen
import ru.alexgladkov.odyssey.compose.extensions.tab
import ru.alexgladkov.odyssey.compose.navigation.RootComposeBuilder

fun RootComposeBuilder.mainFlow() {
	bottomNavigation(
		name = NavigationTree.Main.Dashboard.name,
		tabsNavModel = BottomConfiguration()
	) {
		tab(DeptTab()) {
			screen(name = NavigationTree.Main.Depts.name) {
				DeptScreen()
			}
		}

		tab(UserTab()) {
			screen(name = NavigationTree.Main.Users.name) {
				UserScreen()
			}
		}

		tab(AwardTab()) {
			screen(name = NavigationTree.Main.Awards.name) {
				TestScreen()
			}
		}

		tab(ProfileTab()) {
			screen(name = NavigationTree.Main.Profile.name) {
				ProfileScreen()
			}
		}
	}
}