package uz.gita.data.remote.request

import com.google.gson.annotations.SerializedName

internal sealed class AuthRequestModel {
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
    ) : AuthRequestModel()

    data class SignUpVerify(val token: String, val code: String) : AuthRequestModel()
    data class SignInRequest(val phone: String, val password: String) : AuthRequestModel()
    data class SignInVerify(val token: String, val code: String) : AuthRequestModel()
    data class UpdateToken(@SerializedName("refresh-token") val refreshToken: String) : AuthRequestModel()
    data class SignInResend(val token: String) : AuthRequestModel()
    data class SignUpResend(val token: String) : AuthRequestModel()
}