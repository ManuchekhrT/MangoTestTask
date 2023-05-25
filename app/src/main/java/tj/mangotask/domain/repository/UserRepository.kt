package tj.mangotask.domain.repository

import tj.mangotask.data.model.*

interface UserRepository {
    suspend fun getAndSyncCurrentUser(): Profile
    suspend fun getCurrentUser(): Profile
    suspend fun updateUser(param: UserParam): Avatar
    suspend fun getSavedUser(): Profile
    suspend fun getTokens(): UserToken
    suspend fun clearTokens()
}