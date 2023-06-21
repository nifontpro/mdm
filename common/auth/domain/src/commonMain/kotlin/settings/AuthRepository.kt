package settings

import settings.model.User
import settings.model.base.BaseResponse

interface AuthRepository {
    suspend fun getProfiles(): BaseResponse<List<User>>
}