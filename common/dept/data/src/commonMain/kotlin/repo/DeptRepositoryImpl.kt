package repo

import model.Dept
import model.request.GetDeptsByParentIdRequest
import model.response.BaseResponse
import rest.DeptRemoteDataSource

class DeptRepositoryImpl(
	private val deptRemoteDataSource: DeptRemoteDataSource
) : DeptRepository {

	override suspend fun getDeptByParentId(request: GetDeptsByParentIdRequest): BaseResponse<List<Dept>> {
		return deptRemoteDataSource.getDeptByParentId(request = request)
	}

}