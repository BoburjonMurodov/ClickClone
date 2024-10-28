package uz.gita.boboor.bankingappcompose.domain.usecase

import kotlinx.coroutines.flow.Flow
import uz.gita.common.models.request.AuthRequestParam

interface SignInUC {
    operator fun invoke(singIn: AuthRequestParam.SignInRequest): Flow<Result<Unit>>
}