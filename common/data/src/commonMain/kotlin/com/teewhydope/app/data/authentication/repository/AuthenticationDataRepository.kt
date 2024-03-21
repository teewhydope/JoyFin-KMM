package com.teewhydope.app.data.authentication.repository

import com.teewhydope.app.data.authentication.datasource.remote.AuthenticationRemoteSource
import com.teewhydope.app.data.authentication.mapper.AuthenticationDataToDomainExceptionMapper
import com.teewhydope.app.data.authentication.mapper.LoginDataToDomainModelMapper
import com.teewhydope.app.data.authentication.mapper.LoginRequestDomainToDataModelMapper
import com.teewhydope.app.data.common.network.extension.doOrThrowException
import com.teewhydope.app.domain.authentication.model.LoginResponseDomainModel
import com.teewhydope.app.domain.authentication.model.LoginWithEmailAndPasswordRequestDomainModel
import com.teewhydope.app.domain.authentication.repository.AuthenticationRepository

class AuthenticationDataRepository(
    private val authenticationRemoteSource: AuthenticationRemoteSource,
    private val loginRequestDomainToDataModelMapper: LoginRequestDomainToDataModelMapper,
    private val loginDataToDomainModelMapper: LoginDataToDomainModelMapper,
    private val authenticationDataToDomainExceptionMapper: AuthenticationDataToDomainExceptionMapper
) : AuthenticationRepository {
    override suspend fun login(request: LoginWithEmailAndPasswordRequestDomainModel): LoginResponseDomainModel =
        doOrThrowException(
            execute = {
                val requestDataModel = loginRequestDomainToDataModelMapper.toData(request)
                val loginDataModel =
                    authenticationRemoteSource.loginWithEmailAndPassword(requestDataModel)
                authenticationRemoteSource.accountVerificationState(loginDataModel.userData)
                loginDataToDomainModelMapper.toDomain(loginDataModel)
            },
            exceptionHandler = { throwable ->
                authenticationDataToDomainExceptionMapper.toDomain(throwable)
            }
        )
}

