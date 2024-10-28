package uz.gita.data.repository

import kotlinx.coroutines.flow.Flow
import uz.gita.common.models.request.AuthRequestParam

interface AuthRepository {
    suspend fun signUp(signUp: AuthRequestParam.SignUpRequest): Flow<Result<Unit>>
    suspend fun signIn(signIn: AuthRequestParam.SignInRequest): Flow<Result<Unit>>
    suspend fun signUpVerify(signUpVerify: AuthRequestParam.SignUpVerify): Flow<Result<Unit>>
    suspend fun signInVerify(signInVerify: AuthRequestParam.SignInVerify): Flow<Result<Unit>>
    suspend fun signUpResend(signUpResend: AuthRequestParam.SignUpResend): Flow<Result<Unit>>
    suspend fun signInResend(singInResend: AuthRequestParam.SignInResend): Flow<Result<Unit>>
    suspend fun updateToken(token: AuthRequestParam.UpdateToken): Flow<Result<Unit>>
}