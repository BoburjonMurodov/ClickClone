package uz.gita.common.models.response

import com.google.gson.annotations.SerializedName


/*
    Created by Boburjon Murodov 27/10/24 at 17:24
*/


sealed interface HomeResponse {
    data class BalanceResponse(
        @SerializedName("total-balance") val totalBalance: Double
    ) : HomeResponse

    data class BasicInfoResponse(
        val name: String,
        val email: String,
        val phone: String,
        val gender: String,
        @SerializedName("birth-date") val birthDate: String
    ) : HomeResponse

    data class FullInfoResponse(
        val name: String,
        val email: String,
        val phone: String,
        val gender: String,
        @SerializedName("birth-date") val birthDate: String,
        val address: String,
        val nationality: String
    ) : HomeResponse
}