package settings

import settings.model.User

interface AuthRepository {
    suspend fun getProfiles(): List<User>
}