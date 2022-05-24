package com.shahin.cleancompose.network

import com.shahin.cleancompose.network.error.ErrorReason

sealed class NetworkResponse<out T> {
    data class Success<T>(val result: T?): NetworkResponse<T>()
    data class Failure<T>(val errorReason: ErrorReason): NetworkResponse<T>()
    object NetworkError: NetworkResponse<Nothing>()
}
