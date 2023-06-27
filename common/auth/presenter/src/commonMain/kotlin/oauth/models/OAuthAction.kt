package oauth.models

sealed class OAuthAction {
	object LoginAction : OAuthAction()
	object LogoutAction : OAuthAction()
	object OpenMainFlow : OAuthAction()
}
