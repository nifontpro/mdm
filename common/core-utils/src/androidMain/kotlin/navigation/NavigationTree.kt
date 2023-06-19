package navigation

object NavigationTree {

	enum class Splash {
		SplashScreen
	}

	enum class Auth {
		AuthFlow, Login, OAuth, Forgot
	}

	enum class Main {
		Dashboard, Home, Search, Event, Videos, Profile, Game
	}
}