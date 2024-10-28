package uz.gita.usecase.auth.impl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import uz.gita.boboor.bankingappcompose.domain.usecase.SignInUC
import uz.gita.data.repository.AuthRepository
import uz.gita.common.models.request.AuthRequestParam
import javax.inject.Inject

internal class SignInUCImpl @Inject constructor(private val repository: uz.gita.data.repository.AuthRepository) : SignInUC {
    override fun invoke(singIn: AuthRequestParam.SignInRequest): Flow<Result<Unit>> = flow {
        repository.signIn(singIn)
            .collect { emit(it) }
    }
}