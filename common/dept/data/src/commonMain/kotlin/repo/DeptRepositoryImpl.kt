package repo

import model.Dept
import model.request.GetCurrentDeptsRequest
import model.response.BaseResponse
import rest.DeptRemoteDataSource

class DeptRepositoryImpl(
	private val remoteDataSource: DeptRemoteDataSource
) : DeptRepository {

	override suspend fun getCurrentDepts(request: GetCurrentDeptsRequest): BaseResponse<List<Dept>> {
		return remoteDataSource.getCurrentDepts(request = request)
	}

}