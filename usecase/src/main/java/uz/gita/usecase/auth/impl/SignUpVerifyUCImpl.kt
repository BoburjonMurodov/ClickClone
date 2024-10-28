package uz.gita.usecase.auth.impl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import uz.gita.data.repository.AuthRepository
import uz.gita.boboor.bankingappcompose.domain.usecase.SignUpVerifyUC
import uz.gita.common.models.request.AuthRequestParam
import javax.inject.Inject

internal class SignUpVerifyUCImpl @Inject constructor(private val repository: uz.gita.data.repository.AuthRepository) : SignUpVerifyUC {
    override suspend fun invoke(singUp: AuthRequestParam.SignUpVerify): Flow<Result<Unit>> = flow {
        repository.signUpVerify(singUp)
            .collect { emit(it) }
    }
}