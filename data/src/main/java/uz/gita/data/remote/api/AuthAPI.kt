package uz.gita.data.remote.api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import uz.gita.data.remote.request.AuthRequestModel
import uz.gita.data.remote.response.AuthResponseModel

internal interface AuthAPI {
    @POST("auth/sign-up")
    suspend fun register(@Body registerRequest: AuthRequestModel.SignUpRequest): Response<AuthResponseModel.AuthResponse>

    @POST("auth/sign-up/verify")
    suspend fun registerVerify(@Body signUpVerify: AuthRequestModel.SignUpVerify): Response<AuthResponseModel.TokenResponse>

    @POST("auth/sign-in")
    suspend fun signIn(@Body signInRequest: AuthRequestModel.SignInRequest): Response<AuthResponseModel.AuthResponse>

    @POST("auth/sign-in/verify")
    suspend fun signInVerify(@Body signInVerify: AuthRequestModel.SignInVerify): Response<AuthResponseModel.TokenResponse>

    @POST("auth/update-token")
    suspend fun updateToken(@Body updateToken: AuthRequestModel.UpdateToken): Response<AuthResponseModel.TokenResponse>

    @POST("auth/sign-in/resend")
    suspend fun signInResend(@Body signInResend: AuthRequestModel.SignInResend): Response<AuthResponseModel.AuthResponse>

    @POST("auth/sign-up/resend")
    suspend fun signUpResend(@Body signUpResend: AuthRequestModel.SignUpResend): Response<AuthResponseModel.AuthResponse>
}