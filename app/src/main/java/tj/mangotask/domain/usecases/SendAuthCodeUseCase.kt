package tj.mangotask.domain.usecases

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import tj.mangotask.data.model.PhoneSuccess
import tj.mangotask.domain.model.State
import tj.mangotask.domain.repository.AuthRepository
import javax.inject.Inject

data class PhoneParam(val phone: String)

interface SendAuthCodeUseCase : FlowUseCase<PhoneParam, PhoneSuccess>

class SendAuthCodeUseCaseImpl @Inject constructor(
    private val repository: AuthRepository
) : SendAuthCodeUseCase {

    override fun execute(param: PhoneParam): Flow<Result<PhoneSuccess>> = flow {
        emit(Result.success(repository.sendAuthCode(param.phone)))
    }
}