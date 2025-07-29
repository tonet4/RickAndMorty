package com.example.rickandmorty.data.network_manager

import retrofit2.Response

class NetworkManager {

    suspend fun <T> getResponse(call: suspend () -> Response<T>): T {
        try {
            val response = call()
            return if (response.isSuccessful) {
                response.body() ?: throw DataException("", DataErrorType.UNKNOWN)
            } else {
                val responseCode = response.code()
                val dataErrorType = when (responseCode) {
                    in 400..499 -> DataErrorType.CLIENT
                    in 500..599 -> DataErrorType.SERVER
                    else -> DataErrorType.UNKNOWN
                }
                throw DataException(responseCode.toString(), dataErrorType)
            }
        } catch (e: Exception) {
            throw DataException("", DataErrorType.CONNECTION)
        }
    }
}