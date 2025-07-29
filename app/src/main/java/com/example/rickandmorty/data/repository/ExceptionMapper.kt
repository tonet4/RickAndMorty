package com.example.rickandmorty.data.repository

import com.example.rickandmorty.data.network_manager.DataException
import com.example.rickandmorty.domain.exception.DomainError
import com.example.rickandmorty.domain.exception.DomainException

private const val ERROR_CODE_BAD_REQUEST = "400"
private const val ERROR_CODE_UNAUTHORIZED = "401"
private const val ERROR_CODE_NOT_FOUND = "404"
private const val ERROR_CODE_INTERNAL_SERVER = "500"
private const val ERROR_CODE_CONNECTION = "0"

object ExceptionMapper {

    fun DataException.toDomain(): DomainException {
        val error = if (errorCode.isNotEmpty()) {
            when (errorCode) {
                ERROR_CODE_BAD_REQUEST -> DomainError.BadRequest
                ERROR_CODE_UNAUTHORIZED -> DomainError.Unauthorized
                ERROR_CODE_NOT_FOUND -> DomainError.NotFound
                ERROR_CODE_INTERNAL_SERVER -> DomainError.InternalServer
                ERROR_CODE_CONNECTION -> DomainError.Connection
                else -> DomainError.Unknown
            }
        } else {
            DomainError.Unknown
        }

        return DomainException(
            error = error
        )
    }
}