package com.teewhydope.app.ios.data.dependencyinjection

import com.teewhydope.app.domain.common.usecase.UseCaseExecutor
import com.teewhydope.app.ios.data.dependencyinjection.authentication.AuthenticationRepositoryProvider
import com.teewhydope.app.ios.data.dependencyinjection.authentication.AuthenticationUseCaseFactory
import com.teewhydope.app.ios.data.dependencyinjection.network.NetworkDataModule
import com.teewhydope.app.logger.GlobalLogger
import com.teewhydope.app.logger._globalLogger
import kotlinx.coroutines.MainScope

class AppDIContainer(
    iOSGlobalLogger: GlobalLogger,
) {
    init {
        _globalLogger = iOSGlobalLogger
    }

    private val networkDataModule = NetworkDataModule()

    val useCaseExecutor = UseCaseExecutor(coroutineScope = MainScope())

    private val networkClient = networkDataModule.networkClient

    private val authenticationRepository = AuthenticationRepositoryProvider(
        networkClient = networkClient
    ).authenticationRepository

    val authentication = AuthenticationUseCaseFactory(
        authenticationRepository = authenticationRepository
    )
}

