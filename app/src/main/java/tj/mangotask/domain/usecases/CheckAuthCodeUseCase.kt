package tj.mangotask.domain.usecases

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import tj.mangotask.data.model.UserToken
import tj.mangotask.domain.model.State
import tj.mangotask.domain.repository.AuthRepository
import javax.inject.Inject

data class PhoneWithCodeParam(val phone: String, val code: String)

interface CheckAuthCodeUseCase : FlowUseCase<PhoneWithCodeParam, UserToken>

class CheckAuthCodeUseCaseImpl @Inject constructor(
    private val repository: AuthRepository
) : CheckAuthCodeUseCase {

    override fun execute(param: PhoneWithCodeParam): Flow<Result<UserToken>> = flow {
        emit(Result.success(repository.checkAuthCode(phone = param.phone, code = param.code)))
    }
}