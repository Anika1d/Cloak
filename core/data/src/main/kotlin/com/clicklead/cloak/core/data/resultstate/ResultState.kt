package com.clicklead.cloak.core.data.resultstate


sealed class ResultState<out T : Any> {
    data class Success<out T : Any>(val data: T?) : ResultState<T>()
    data class Error<out T : Any>(val error: Exception) : ResultState<T>()
}