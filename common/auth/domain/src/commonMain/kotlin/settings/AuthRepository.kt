package settings

import user.User
import base.BaseResponse

interface AuthRepository {
	suspend fun getProfiles(): BaseResponse<List<User>>
	suspend fun isUserLoggedIn(): Boolean
}