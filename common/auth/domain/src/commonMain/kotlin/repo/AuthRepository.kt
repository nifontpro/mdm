package repo

import model.response.BaseResponse
import user.User

interface AuthRepository {
	suspend fun getProfiles(): BaseResponse<List<User>>
	suspend fun isUserLoggedIn(): Boolean
}