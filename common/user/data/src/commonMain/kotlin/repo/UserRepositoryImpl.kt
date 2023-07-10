package repo

import model.User
import model.request.GetUsersByDeptRequest
import model.response.BaseResponse
import rest.UserRemoteDataSource

class UserRepositoryImpl(
	private val userRemoteDataSource: UserRemoteDataSource,
) : UserRepository {

	override suspend fun getByDept(request: GetUsersByDeptRequest): BaseResponse<List<User>> {
		return userRemoteDataSource.getByDept(request = request)
	}

}