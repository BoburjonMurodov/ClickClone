package uz.gita.boboor.bankingappcompose.domain.usecase

import kotlinx.coroutines.flow.Flow
import uz.gita.common.models.request.AuthRequestParam


interface SignUpUC {
    operator fun invoke(singUp: AuthRequestParam.SignUpRequest): Flow<Result<Unit>>
}