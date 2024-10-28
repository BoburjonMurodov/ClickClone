package uz.gita.usecase.auth.impl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import uz.gita.data.repository.AuthRepository
import uz.gita.boboor.bankingappcompose.domain.usecase.SignUpResendUC
import uz.gita.common.models.request.AuthRequestParam
import javax.inject.Inject

internal class SignUpResendUCImpl @Inject constructor(private val repository: uz.gita.data.repository.AuthRepository) : SignUpResendUC {
    override suspend fun invoke(): Flow<Result<Unit>> = flow {
        repository.signInResend(AuthRequestParam.SignInResend)
            .collect { emit(it) }
    }
}