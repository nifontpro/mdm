package repo

import model.Dept
import model.request.GetDeptsByParentIdRequest
import model.response.BaseResponse

interface DeptRepository {
	suspend fun getDeptByParentId(request: GetDeptsByParentIdRequest): BaseResponse<List<Dept>>
}