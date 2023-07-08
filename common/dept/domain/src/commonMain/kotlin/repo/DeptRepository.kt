package repo

import model.Dept
import model.request.GetAuthDeptRequest
import model.request.GetCurrentDeptsRequest
import model.request.GetDeptByIdRequest
import model.response.BaseResponse

interface DeptRepository {
	suspend fun getCurrentDepts(request: GetCurrentDeptsRequest): BaseResponse<List<Dept>>
	suspend fun getAuthDept(request: GetAuthDeptRequest): BaseResponse<Dept>
	suspend fun getDeptById(request: GetDeptByIdRequest): BaseResponse<Dept>
}