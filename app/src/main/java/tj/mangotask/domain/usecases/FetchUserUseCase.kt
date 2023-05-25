package tj.mangotask.domain.usecases

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import tj.mangotask.data.model.Profile
import tj.mangotask.domain.model.State
import tj.mangotask.domain.repository.UserRepository
import javax.inject.Inject

interface FetchUserUseCase : FlowUseCase<Boolean, Profile>

class FetchUserUseCaseImpl @Inject constructor(
    private val repository: UserRepository
) : FetchUserUseCase {

    override fun execute(param: Boolean): Flow<Result<Profile>> = flow {
        if (param) {
            emit(Result.success(repository.getAndSyncCurrentUser()))
        } else {
            emit(Result.success(repository.getCurrentUser()))
        }
    }

}