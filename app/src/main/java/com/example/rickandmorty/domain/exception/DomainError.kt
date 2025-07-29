package com.example.rickandmorty.domain.exception

import androidx.annotation.StringRes
import com.example.rickandmorty.R

sealed class DomainError(@StringRes val messageId: Int) {
    data object NoError: DomainError(R.string.error_no_error)
    data object BadRequest : DomainError(R.string.error_bad_request)
    data object NotFound : DomainError(R.string.error_not_found)
    data object Unauthorized : DomainError(R.string.error_unauthorized)
    data object InternalServer : DomainError(R.string.error_internal_server)
    data object Connection : DomainError(R.string.error_connection)
    data object Unknown : DomainError(R.string.error_unknown)
}