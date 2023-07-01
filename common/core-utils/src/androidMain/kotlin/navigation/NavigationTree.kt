package navigation

object NavigationTree {

	enum class Splash {
		SplashScreen
	}

	enum class Auth {
		AuthFlow, OAuth
	}

	enum class Main {
		Dashboard, Home, Depts, Users, Awards, Profile
	}
}