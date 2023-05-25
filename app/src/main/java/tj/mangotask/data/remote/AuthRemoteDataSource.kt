package tj.mangotask.data.remote

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import tj.mangotask.data.remote.dtos.*
import tj.mangotask.data.remote.request.PhoneRequest
import tj.mangotask.data.remote.request.PhoneWithCodeRequest
import tj.mangotask.data.remote.request.RegisterRequest

class AuthRemoteDataSource(
    private val api: AuthApi,
    private val ioDispatcher: CoroutineDispatcher
) {
    suspend fun sendAuthCode(phone: String): PhoneDto =
        withContext(ioDispatcher) {
            api.sendAuthCode(PhoneRequest(phone))
        }

    suspend fun checkAuthCode(phone: String, code: String): PhoneWithCodeDto =
        withContext(ioDispatcher) {
            api.checkAuthCode(PhoneWithCodeRequest(phone = phone, code = code))
        }

    suspend fun register(name: String, username: String, phone: String): RegisterDto =
        withContext(ioDispatcher) {
            api.register(RegisterRequest(phone = phone, name = name, username = username))
        }
}