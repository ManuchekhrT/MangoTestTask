package tj.mangotask.data.remote

import kotlinx.coroutines.Deferred
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import tj.mangotask.data.remote.dtos.*
import tj.mangotask.data.remote.request.RefreshTokenRequest
import tj.mangotask.data.remote.request.UserRequest

interface UserApi {
    @GET("api/v1/users/me/")
    suspend fun getCurrentUser(): ProfileDataDto

    @PUT("api/v1/users/me/")
    suspend fun updateUser(
        @Body request: UserRequest
    ): AvatarsDto
}