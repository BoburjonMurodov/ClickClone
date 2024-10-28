package uz.gita.boboor.bankingappcompose.domain.usecase

import kotlinx.coroutines.flow.Flow
import uz.gita.common.models.request.AuthRequestParam

interface SignInVerifyUC {
    operator suspend fun invoke(singIn: AuthRequestParam.SignInVerify): Flow<Result<Unit>>
}