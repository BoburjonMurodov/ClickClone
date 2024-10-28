package uz.gita.data.repository.impl

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import uz.gita.boboor.bankingappcompose.data.remote.mappers.toRequest
import uz.gita.common.models.request.AuthRequestParam
import uz.gita.data.extensions.flowWithCatch
import uz.gita.data.extensions.toResult
import uz.gita.data.local.LocalStorage
import uz.gita.data.remote.api.AuthAPI
import uz.gita.data.repository.AuthRepository
import javax.inject.Inject

internal class AuthRepositoryImpl @Inject constructor(
    private val api: AuthAPI,
    private val localStorage: LocalStorage
) : AuthRepository {

    override suspend fun signUp(signUp: AuthRequestParam.SignUpRequest): Flow<Result<Unit>> = flowWithCatch {
        delay(1000)
        emit(api.register(signUp.toRequest()).toResult { localStorage.token = it.token })
    }

    override suspend fun signIn(signIn: AuthRequestParam.SignInRequest): Flow<Result<Unit>> = flowWithCatch {
        delay(1000)
        emit(api.signIn(signIn.toRequest()).toResult { localStorage.token = it.token })
    }


    override suspend fun signUpResend(signUpResend: AuthRequestParam.SignUpResend): Flow<Result<Unit>> = flowWithCatch {
        emit(api.signUpResend(signUpResend.toRequest(localStorage.token)).toResult { localStorage.token = it.token })
    }

    override suspend fun signInResend(singInResend: AuthRequestParam.SignInResend): Flow<Result<Unit>> = flowWithCatch {
        emit(api.signInResend(singInResend.toRequest(localStorage.token)).toResult { localStorage.token = it.token })
    }

    override suspend fun updateToken(token: AuthRequestParam.UpdateToken): Flow<Result<Unit>> = flowWithCatch {
        emit(api.updateToken(token.toRequest(localStorage.refreshToken)).toResult {
            localStorage.accessToken = it.accessToken
            localStorage.refreshToken = it.refreshToken
        })
    }

    override suspend fun signUpVerify(signUpVerify: AuthRequestParam.SignUpVerify): Flow<Result<Unit>> = flowWithCatch {
        emit(api.registerVerify(signUpVerify.toRequest(localStorage.token)).toResult {
            localStorage.accessToken = it.accessToken
            localStorage.refreshToken = it.refreshToken
        })
    }

    override suspend fun signInVerify(signInVerify: AuthRequestParam.SignInVerify): Flow<Result<Unit>> = flowWithCatch {
        emit(api.signInVerify(signInVerify.toRequest(localStorage.token)).toResult {
            localStorage.accessToken = it.accessToken
            localStorage.refreshToken = it.refreshToken
        })
    }
}

