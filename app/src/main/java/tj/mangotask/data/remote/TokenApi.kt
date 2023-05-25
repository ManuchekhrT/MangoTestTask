package tj.mangotask.data.remote

import kotlinx.coroutines.Deferred
import retrofit2.http.Body
import retrofit2.http.POST
import tj.mangotask.data.remote.dtos.RefreshTokenDto
import tj.mangotask.data.remote.request.RefreshTokenRequest

interface TokenApi {
    @POST("api/v1/users/refresh-token/")
    fun refreshTokenAsync(
        @Body request: RefreshTokenRequest
    ): Deferred<RefreshTokenDto>
}