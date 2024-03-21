package com.teewhydope.app.domain.common.usecase

import com.teewhydope.app.domain.common.model.exception.DomainException
import com.teewhydope.app.domain.common.model.exception.UnknownDomainException

interface BaseUseCase<REQUEST, RESULT> {
    suspend fun execute(value: REQUEST, callback: (RESULT) -> Unit)

    fun onError(throwable: Throwable): DomainException = UnknownDomainException(throwable)
}