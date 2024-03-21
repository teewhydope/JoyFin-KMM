package com.teewhydope.app.domain.common.usecase

import com.teewhydope.app.domain.common.model.exception.DomainException
import com.teewhydope.app.domain.common.model.exception.UnknownDomainException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.coroutines.cancellation.CancellationException

class UseCaseExecutor(private val coroutineScope: CoroutineScope) {
    fun <OUTPUT> run(
        useCase: BaseUseCase<Unit, OUTPUT>,
        onResult: (OUTPUT) -> Unit = {},
        onException: (DomainException) -> Unit = {},
    ) = run(useCase, Unit, onResult, onException)

    fun <INPUT, OUTPUT> run(
        useCase: BaseUseCase<INPUT, OUTPUT>,
        value: INPUT,
        onResult: (OUTPUT) -> Unit = {},
        onException: (DomainException) -> Unit = {},
    ) {
        coroutineScope.launch {
            try {
                useCase.execute(value, onResult)
            } catch (ignore: CancellationException) {
            } catch (@Suppress("TooGenericExceptionCaught") throwable: Throwable) {
                onException(
                    (throwable as? DomainException)
                        ?: UnknownDomainException(throwable),
                )
            }
        }
    }
}