package repo

import model.User
import model.request.GetUsersByDeptRequest
import model.response.BaseResponse

interface UserRepository {
	suspend fun getByDept(request: GetUsersByDeptRequest): BaseResponse<List<User>>
}