package uz.gita.usecase.auth.impl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import uz.gita.data.repository.AuthRepository
import uz.gita.boboor.bankingappcompose.domain.usecase.SignInVerifyUC
import uz.gita.common.models.request.AuthRequestParam
import javax.inject.Inject

internal class SignInVerifyUCImpl @Inject constructor(private val repository: uz.gita.data.repository.AuthRepository) : SignInVerifyUC {
    override suspend fun invoke(singIn: AuthRequestParam.SignInVerify): Flow<Result<Unit>> = flow {
        repository.signInVerify(singIn)
            .collect { emit(it) }
    }
}