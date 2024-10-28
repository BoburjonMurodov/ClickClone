package uz.gita.data.remote.response

import com.google.gson.annotations.SerializedName


internal sealed class AuthResponseModel {
    data class AuthResponse(val token: String) : AuthResponseModel()
    data class TokenResponse(
        @SerializedName("refresh-token") val refreshToken: String,
        @SerializedName("access-token") val accessToken: String
    ) : AuthResponseModel()


}