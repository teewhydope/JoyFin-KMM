package com.teewhydope.app.ios.data.dependencyinjection.authentication

import com.teewhydope.app.data.authentication.datasource.remote.AuthenticationRemoteDataSource
import com.teewhydope.app.data.authentication.datasource.remote.AuthenticationService
import com.teewhydope.app.data.authentication.mapper.AuthenticationDataToDomainExceptionMapper
import com.teewhydope.app.data.authentication.mapper.LoginDataToDomainModelMapper
import com.teewhydope.app.data.authentication.mapper.LoginRequestDataToApiModelMapper
import com.teewhydope.app.data.authentication.mapper.LoginRequestDomainToDataModelMapper
import com.teewhydope.app.data.authentication.mapper.LoginResponseApiToDataModelMapper
import com.teewhydope.app.data.authentication.mapper.SmartSaverTransactionApiToDataModelMapper
import com.teewhydope.app.data.authentication.mapper.SmartSaverTransactionDataToDomainModelMapper
import com.teewhydope.app.data.authentication.mapper.UserDataApiToDataModelMapper
import com.teewhydope.app.data.authentication.mapper.UserDataToDomainModelMapper
import com.teewhydope.app.data.authentication.repository.AuthenticationDataRepository
import com.teewhydope.app.domain.authentication.repository.AuthenticationRepository
import io.ktor.client.HttpClient

class AuthenticationRepositoryProvider(
    private val networkClient: HttpClient,
) {

    val authenticationRepository: AuthenticationRepository = AuthenticationDataRepository(
        authenticationRemoteSource = makeAuthenticationRemoteSource(),
        loginRequestDomainToDataModelMapper = makeLoginRequestDomainToDataModelMapper(),
        loginDataToDomainModelMapper = makeLoginDataToDomainModelMapper(),
        authenticationDataToDomainExceptionMapper = makeAuthenticationDataToDomainExceptionMapper()

    )

    private fun makeAuthenticationRemoteSource() =
        AuthenticationRemoteDataSource(
            authenticationService = makeAuthenticationService(),
            loginRequestDataToApiModelMapper = makeLoginRequestDataToApiModelMapper(),
            loginResponseApiToDataModelMapper = makeLoginResponseApiToDataModelMapper()
        )

    private fun makeAuthenticationService() =
        AuthenticationService(httpClient = networkClient)

    private fun makeAuthenticationDataToDomainExceptionMapper() =
        AuthenticationDataToDomainExceptionMapper()

    private fun makeSmartSaverTransactionApiToDataModelMapper() =
        SmartSaverTransactionApiToDataModelMapper()

    private fun makeUserDataApiToDataModelMapper() =
        UserDataApiToDataModelMapper()

    private fun makeLoginResponseApiToDataModelMapper() =
        LoginResponseApiToDataModelMapper(
            makeUserDataApiToDataModelMapper(),
            makeSmartSaverTransactionApiToDataModelMapper()
        )

    private fun makeSmartSaverTransactionDataToDomainModelMapper() =
        SmartSaverTransactionDataToDomainModelMapper()

    private fun makeUserDataToDomainModelMapper() =
        UserDataToDomainModelMapper()

    private fun makeLoginDataToDomainModelMapper() =
        LoginDataToDomainModelMapper(
            makeUserDataToDomainModelMapper(),
            makeSmartSaverTransactionDataToDomainModelMapper()
        )

    private fun makeLoginRequestDataToApiModelMapper() =
        LoginRequestDataToApiModelMapper()

    private fun makeLoginRequestDomainToDataModelMapper() = LoginRequestDomainToDataModelMapper()
}