package com.example.ckprojectstructure_android.data.api

import com.example.ckprojectstructure_android.util.extension.toJsonString

/**
 * A generic class that holds a value with its loading status.
 * @param <T>
 */
sealed class ApiState<out R> {

    object Loading : ApiState<Nothing>()
    data class Success<out T>(val data: T) : ApiState<T>()
    data class Fail(val message: String) : ApiState<Nothing>()
    data class Error(val throwable: Throwable) : ApiState<Nothing>()
    object Done : ApiState<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Loading -> "Loading"
            is Success<*> -> "Success[data=${data.toJsonString()}]"
            is Fail -> "Fail[message=$message]"
            is Error -> "Error[throwable=${throwable.localizedMessage}]"
            Done -> "Done"
        }
    }
}

/**
 * `true` if [ApiState] is of type [Success] & holds non-null [Success.data].
 */
val ApiState<*>.successfully
    get() = this is ApiState.Success && data != null

fun <T> Result<T>.successOr(fallback: T): T {
    return (this as? ApiState.Success<T>)?.data ?: fallback
}