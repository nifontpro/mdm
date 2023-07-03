package repo

import model.Dept
import model.request.GetCurrentDeptsRequest
import model.response.BaseResponse
import rest.DeptRemoteDataSource

class DeptRepositoryImpl(
	private val deptRemoteDataSource: DeptRemoteDataSource,
//	private val currentSettings: CurrentSettings
) : DeptRepository {

	override suspend fun getCurrentDepts(request: GetCurrentDeptsRequest): BaseResponse<List<Dept>> {
		return deptRemoteDataSource.getCurrentDepts(request = request)
	}

}