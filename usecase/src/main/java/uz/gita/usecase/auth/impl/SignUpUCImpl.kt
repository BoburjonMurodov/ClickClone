package uz.gita.usecase.auth.impl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import uz.gita.data.repository.AuthRepository
import uz.gita.boboor.bankingappcompose.domain.usecase.SignUpUC
import uz.gita.common.models.request.AuthRequestParam
import javax.inject.Inject

internal class SignUpUCImpl @Inject constructor(private val repository: uz.gita.data.repository.AuthRepository) : SignUpUC {
    override fun invoke(singUp: AuthRequestParam.SignUpRequest): Flow<Result<Unit>> = flow {
        repository.signUp(singUp)
            .collect { emit(it) }
    }
}