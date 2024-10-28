package uz.gita.data.extensions

import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import uz.gita.common.ErrorMessage


/*
    Created by Boburjon Murodov 17/10/24 at 19:14
*/


internal fun <T> flowWithCatch(block: suspend FlowCollector<Result<T>>.() -> Unit): Flow<Result<T>> = flow {
    block(this)
}.flowOn(Dispatchers.IO).catch { emit(Result.failure(it)) }

val gson = Gson()

fun <T> handleResult(result: Response<T>, onSuccess: (T) -> Unit): Result<T> {
    return if (result.isSuccessful && result.body() != null) {
        onSuccess(result.body()!!)
        Result.success(result.body()!!)
    } else {
        val error = gson.fromJson(result.errorBody()!!.string(), ErrorMessage::class.java);
        Result.failure(Exception(error.message))
    }
}


suspend fun <T, R> Response<T>.toResult(
    successBlock: suspend (T) -> R
): Result<R> {
    val gson = Gson()
    return when {
        isSuccessful -> Result.success(successBlock(body()!!))

        errorBody() != null -> {
            val errorResponse = gson.fromJson(errorBody()!!.string(), ErrorMessage::class.java)
            Result.failure(Exception(errorResponse.message))
        }

        else -> Result.failure(Exception("Unknown error"))
    }
}

