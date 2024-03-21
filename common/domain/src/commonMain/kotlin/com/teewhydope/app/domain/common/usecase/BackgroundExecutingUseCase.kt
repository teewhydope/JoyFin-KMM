package com.teewhydope.app.domain.common.usecase

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

abstract class BackgroundExecutingUseCase<REQUEST, RESULT> : BaseUseCase<REQUEST, RESULT> {
    final override suspend fun execute(value: REQUEST, callback: (RESULT) -> Unit) {
        val result = withContext(Dispatchers.IO) {
            executeInBackground(value, this)
        }

        callback(result)
    }

    abstract suspend fun executeInBackground(
        request: REQUEST,
        coroutineScope: CoroutineScope
    ): RESULT
}