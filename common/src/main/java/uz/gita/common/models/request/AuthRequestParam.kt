package uz.gita.common.models.request

import com.google.gson.annotations.SerializedName

sealed interface AuthRequestParam {
    data class SignUpRequest(
        val phone: String,
        val password: String,
        @SerializedName("first-name")
        val firstName: String,
        @SerializedName("last-name")
        val lastName: String,
        @SerializedName("born-date")
        val birthDate: String,
        val gender: String
    ) : AuthRequestParam

    data class SignUpVerify(val code: String) : AuthRequestParam
    data class SignInRequest(val phone: String, val password: String) : AuthRequestParam
    data class SignInVerify(val code: String) : AuthRequestParam
    data object UpdateToken : AuthRequestParam
    data object SignInResend : AuthRequestParam
    data object SignUpResend : AuthRequestParam
}