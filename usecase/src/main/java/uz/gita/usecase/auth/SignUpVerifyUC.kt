package uz.gita.boboor.bankingappcompose.domain.usecase

import kotlinx.coroutines.flow.Flow
import uz.gita.common.models.request.AuthRequestParam



interface SignUpVerifyUC {
    suspend operator fun invoke(singUp: AuthRequestParam.SignUpVerify): Flow<Result<Unit>>
}