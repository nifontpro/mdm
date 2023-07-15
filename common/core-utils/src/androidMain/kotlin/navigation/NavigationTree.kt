package navigation

object NavigationTree {

	enum class Splash {
		SplashScreen
	}

	enum class Auth {
		AuthFlow, AuthScreen
	}

	enum class Main {
		Dashboard, Depts, Users, Awards, Profile
	}
}