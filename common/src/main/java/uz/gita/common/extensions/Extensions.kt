package uz.gita.common.extensions

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach


/*
    Created by Boburjon Murodov 17/10/24 at 19:00
*/



fun <T> Flow<Result<T>>.onSuccess(action: suspend ((T) -> Unit)) =
    onEach {
        it.onSuccess {
            action.invoke(it)
        }
    }


fun <T> Flow<Result<T>>.onFailure(action: suspend (Throwable) -> Unit) =
    onEach {
        it.onFailure {
            action.invoke(it)
        }

    }
