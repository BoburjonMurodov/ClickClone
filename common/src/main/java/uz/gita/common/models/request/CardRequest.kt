package uz.gita.common.models.request

import com.google.gson.annotations.SerializedName


/*
    Created by Boburjon Murodov 27/10/24 at 18:52
*/

sealed interface CardRequest {
    data class AddCard(
        @SerializedName("expired-month")
        val expiredMonth: String,
        @SerializedName("expired-year")
        val expiredYear: String,
        @SerializedName("name")
        val name: String,
        @SerializedName("pan")
        val pan: String
    ) : CardRequest

    data class UpdateCard(
        @SerializedName("id")
        val id: Int,
        @SerializedName("is-visible")
        val isVisible: String,
        @SerializedName("name")
        val name: String,
        @SerializedName("theme-type")
        val themeType: Int
    ) : CardRequest

    data class DeleteCard(val id: Int): CardRequest
}