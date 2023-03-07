package com.shahin.cleancompose.network

import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.shahin.cleancompose.network.error.ErrorReason
import com.shahin.cleancompose.network.error.ErrorType
import com.shahin.cleancompose.network.models.ErrorResponse
import retrofit2.Response

open class NetworkWrapper {
    inline fun <T : Any> networkResponseOf(service: () -> Response<T>): NetworkResponse<T> {
        return try {
            val response = service()
            if (response.isSuccessful) {
                NetworkResponse.Success(response.body())
            } else {
                try {
                    val gSon = Gson()
                    val typedValue = gSon.fromJson(
                        response.errorBody()?.string(),
                        ErrorResponse::class.java
                    )
                    NetworkResponse.Failure(
                        ErrorReason(
                            ErrorType.values().find { it.internalCode == typedValue.error.code }
                                ?: ErrorType.Unknown,
                            typedValue.error.message
                        )
                    )
                } catch (e: JsonSyntaxException) {
                    NetworkResponse.Failure(
                        ErrorReason(
                            ErrorType.Unknown,
                            response.message()
                        )
                    )
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            NetworkResponse.NetworkError
        }
    }
}
