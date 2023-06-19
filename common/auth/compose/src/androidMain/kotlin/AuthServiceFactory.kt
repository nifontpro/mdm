import net.openid.appauth.AuthorizationService

class AuthServiceFactory(platformConfiguration: PlatformConfiguration) {
	val authorizationService: AuthorizationService = AuthorizationService(platformConfiguration.androidContext)
}