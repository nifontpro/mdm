package repo

import model.request.GetAuthDeptIdRequest
import user.User
import model.response.BaseResponse

interface AuthRepository {
	suspend fun getProfiles(): BaseResponse<List<User>>
	suspend fun isUserLoggedIn(): Boolean
	suspend fun getAuthDeptId(request: GetAuthDeptIdRequest): BaseResponse<Long>
}