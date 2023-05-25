package tj.mangotask.data.repository

import android.util.Log
import tj.mangotask.data.local.LocalDataSource
import tj.mangotask.data.model.PhoneSuccess
import tj.mangotask.data.model.Register
import tj.mangotask.data.model.UserToken
import tj.mangotask.data.remote.AuthRemoteDataSource
import tj.mangotask.domain.mapper.asEntity
import tj.mangotask.domain.mapper.asExternalModel
import tj.mangotask.domain.mapper.asExternalModel2
import tj.mangotask.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: AuthRemoteDataSource
) : AuthRepository {

    override suspend fun sendAuthCode(phone: String): PhoneSuccess {
        return remoteDataSource.sendAuthCode(phone).asExternalModel()
    }

    override suspend fun checkAuthCode(phone: String, code: String): UserToken {
        val entity = remoteDataSource.checkAuthCode(phone, code).asEntity()
        if (entity.isUserExists) {
            localDataSource.saveTokenData(
                refreshToken = entity.refreshToken ?: "",
                accessToken = entity.accessToken ?: "",
                userId = entity.userId,
                time = System.currentTimeMillis() + 10 * 60 * 1000 //10 min in millis
            )
        }
        return entity.asExternalModel()
    }

    override suspend fun register(phone: String, name: String, username: String): Register {
        val entity =
            remoteDataSource.register(name = name, username = username, phone = phone).asEntity()

        localDataSource.saveTokenData(
            refreshToken = entity.refreshToken ?: "",
            accessToken = entity.accessToken ?: "",
            userId = entity.userId,
            time = System.currentTimeMillis() + 10 * 60 * 1000 //10 min in millis
        )

        return entity.asExternalModel2()
    }
}