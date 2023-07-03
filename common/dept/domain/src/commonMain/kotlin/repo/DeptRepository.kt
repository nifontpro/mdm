package repo

import model.Dept
import model.request.GetCurrentDeptsRequest
import model.response.BaseResponse

interface DeptRepository {
	suspend fun getCurrentDepts(request: GetCurrentDeptsRequest): BaseResponse<List<Dept>>
}