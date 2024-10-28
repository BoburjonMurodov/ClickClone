package uz.gita.boboor.bankingappcompose.utils

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed


/*
    Created by Boburjon Murodov 27/10/24 at 11:17
*/

var lastClickTime = 0L


inline fun Modifier.debounceClickable(
    debounceInterval: Long = 300L,
    isLoading: Boolean = false,
    crossinline onClick: () -> Unit
): Modifier = composed {

    clickable(indication = null, interactionSource = null) {
        val currentTime = System.currentTimeMillis()
        if ((currentTime - lastClickTime) < debounceInterval && !isLoading ) {
            return@clickable
        }
        Log.d("TTT", "debounceClickable: ")
        lastClickTime = currentTime
        onClick()
    }
}