package uz.gita.usecase.auth.impl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import uz.gita.boboor.bankingappcompose.domain.usecase.SignInResendUC
import uz.gita.common.models.request.AuthRequestParam
import uz.gita.data.repository.AuthRepository
import javax.inject.Inject

internal class SignInResendUCImpl @Inject constructor(private val repository: AuthRepository) : SignInResendUC {
    override fun invoke(): Flow<Result<Unit>> = flow {
        repository.signUpResend(AuthRequestParam.SignUpResend)
            .collect { emit(it) }
    }
}