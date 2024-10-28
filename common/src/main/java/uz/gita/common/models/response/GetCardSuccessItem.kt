package uz.gita.common.models.response


import com.google.gson.annotations.SerializedName

data class GetCardSuccessItem(
    @SerializedName("amount")
    val amount: Int,
    @SerializedName("expired-month")
    val expiredMonth: Int,
    @SerializedName("expired-year")
    val expiredYear: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("is-visible")
    val isVisible: Boolean,
    @SerializedName("name")
    val name: String,
    @SerializedName("owner")
    val owner: String,
    @SerializedName("pan")
    val pan: String,
    @SerializedName("theme-type")
    val themeType: Int
)