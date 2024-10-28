package uz.gita.common.models.response

import com.google.gson.annotations.SerializedName


sealed interface AuthResponseParam {
    data class AuthResponse(val token: String) : AuthResponseParam
    data class TokenResponse(
        @SerializedName("refresh-token") val refreshToken: String,
        @SerializedName("access-token") val accessToken: String
    ) : AuthResponseParam
}