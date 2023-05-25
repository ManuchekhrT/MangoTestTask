package tj.mangotask.data.remote

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import tj.mangotask.data.remote.dtos.*
import tj.mangotask.data.remote.request.UserRequest

class UserRemoteDataSource(
    private val api: UserApi,
    private val ioDispatcher: CoroutineDispatcher
) {

    suspend fun fetchCurrentUser(): UserDto = withContext(ioDispatcher) {
        api.getCurrentUser().profileData
    }

    suspend fun updateUser(request: UserRequest): AvatarDto = withContext(ioDispatcher) {
        api.updateUser(request).avatars
    }

}