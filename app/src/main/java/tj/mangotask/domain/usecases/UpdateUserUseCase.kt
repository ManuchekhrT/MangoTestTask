package tj.mangotask.domain.usecases

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import tj.mangotask.data.model.Avatar
import tj.mangotask.data.model.UserParam
import tj.mangotask.domain.model.State
import tj.mangotask.domain.repository.UserRepository
import javax.inject.Inject

interface UpdateUserUseCase : FlowUseCase<UserParam, Avatar>

class UpdateUserUseCaseImpl @Inject constructor(
    private val repository: UserRepository
) : UpdateUserUseCase {

    override fun execute(param: UserParam): Flow<Result<Avatar>> = flow {
        emit(Result.success(repository.updateUser(param)))
    }
}