package repo

import curent.repo.CurrentSettings
import model.Dept
import model.request.GetAuthDeptRequest
import model.request.GetCurrentDeptsRequest
import model.request.GetDeptByIdRequest
import model.response.BaseResponse
import rest.DeptRemoteDataSource

class DeptRepositoryImpl(
	private val deptRemoteDataSource: DeptRemoteDataSource,
	private val currentSettings: CurrentSettings
) : DeptRepository {

	override suspend fun getAuthDept(request: GetAuthDeptRequest): BaseResponse<Dept> {
		val deptId = currentSettings.getCurrentDeptId()
		val parentId = currentSettings.getParentDeptId()

		return if (deptId != 0L && parentId != 0L) {
			BaseResponse.success(data = Dept(id = deptId, parentId = parentId))
		} else {
			deptRemoteDataSource.getAuthDept(request = request)
		}
	}

	override suspend fun getCurrentDepts(request: GetCurrentDeptsRequest): BaseResponse<List<Dept>> {
		return deptRemoteDataSource.getCurrentDepts(request = request)
	}

	override suspend fun getDeptById(request: GetDeptByIdRequest): BaseResponse<Dept> {
		return deptRemoteDataSource.getDeptById(request = request)
	}

}