package repo

import model.RS
import model.response.BaseResponse

interface AuthRepository {
	suspend fun checkAuth(): BaseResponse<RS>
}