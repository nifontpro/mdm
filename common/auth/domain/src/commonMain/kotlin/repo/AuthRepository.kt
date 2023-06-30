package repo

import model.request.GetAuthParentIdRequest
import user.User
import model.response.BaseResponse

interface AuthRepository {
	suspend fun getProfiles(): BaseResponse<List<User>>
	suspend fun isUserLoggedIn(): Boolean
	suspend fun getAuthParentId(request: GetAuthParentIdRequest): BaseResponse<Long>
}