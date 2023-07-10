package repo

import model.User
import model.response.BaseResponse

interface AuthRepository {
	suspend fun getProfiles(): BaseResponse<List<User>>
	suspend fun isUserLoggedIn(): Boolean
}