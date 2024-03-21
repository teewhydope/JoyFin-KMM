package com.teewhydope.app.data.common.network.extension

import com.teewhydope.app.data.common.model.exception.ErrorResponseApiException
import com.teewhydope.app.domain.common.exception.ErrorResponseDomainException
import com.teewhydope.app.domain.common.exception.NoInternetConnectionDomainException
import com.teewhydope.app.domain.common.model.exception.UnknownDomainException
import io.ktor.utils.io.errors.IOException

inline fun <RETURN_TYPE> doOrThrowException(
    execute: () -> RETURN_TYPE,
    noinline exceptionHandler: ((Throwable) -> Throwable)? = null
): RETURN_TYPE {
    try {
        return execute()
    } catch (exception: Exception) {
        throw if (exceptionHandler == null) {
            when (exception) {
                is IOException, is NoInternetConnectionDomainException -> {
                    NoInternetConnectionDomainException(exception)
                }

                is ErrorResponseApiException -> ErrorResponseDomainException(
                    exception,
                    exception.code
                )

                else -> UnknownDomainException(exception)
            }
        } else {
            exceptionHandler(exception)
        }
    }
}