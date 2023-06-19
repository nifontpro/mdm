package oauth.vm

sealed class OAuthAction {
	object LoginAction : OAuthAction()
	object OpenMainFlow : OAuthAction()
}
