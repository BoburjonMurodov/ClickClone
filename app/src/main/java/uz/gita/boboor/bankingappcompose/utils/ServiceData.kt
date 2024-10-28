package uz.gita.boboor.bankingappcompose.utils

import androidx.annotation.IdRes
import androidx.compose.ui.graphics.Color

data class ServiceData(
    val name: String,
    val id: Int,
    val color: Color,
    val type: Services,
    val isNew: Boolean = false
)



enum class Services {
    MY_FAMILY,
    MY_HOME,
    MY_AUTO,
    CHARITY,
    FOOD_DELIVERY,
    FLOWERS,
    UZB_AIRWAYS,
    CINEMA,
    POWER_BANK,
    CLICK_TV,
    GOVERNMENT_SERVICES,
    SELECTED_PAYMENTS
}
