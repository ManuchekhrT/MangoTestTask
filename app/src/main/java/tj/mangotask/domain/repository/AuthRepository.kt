package tj.mangotask.domain.repository

import tj.mangotask.data.model.PhoneSuccess
import tj.mangotask.data.model.Register
import tj.mangotask.data.model.UserToken

interface AuthRepository {
    suspend fun sendAuthCode(phone: String): PhoneSuccess
    suspend fun checkAuthCode(phone: String, code: String): UserToken
    suspend fun register(phone: String, name: String, username: String): Register
}