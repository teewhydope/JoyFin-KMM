package com.teewhydope.app.joyfin.android.di.authentication

import com.teewhydope.app.data.authentication.datasource.remote.AuthenticationRemoteDataSource
import com.teewhydope.app.data.authentication.datasource.remote.AuthenticationRemoteSource
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
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient

@Module
@InstallIn(SingletonComponent::class)
object AuthenticationDataModule {

    @Provides
    @Reusable
    fun providesCustomerService(networkClient: HttpClient) =
        AuthenticationService(networkClient)

    @Provides
    @Reusable
    fun providesCustomerRemoteSource(
        authenticationService: AuthenticationService,
        loginRequestDataToApiModelMapper: LoginRequestDataToApiModelMapper,
        loginResponseApiToDataModelMapper: LoginResponseApiToDataModelMapper,
    ): AuthenticationRemoteSource = AuthenticationRemoteDataSource(
        authenticationService,
        loginRequestDataToApiModelMapper,
        loginResponseApiToDataModelMapper
    )

    @Provides
    @Reusable
    fun providesCustomerRepository(
        authenticationRemoteSource: AuthenticationRemoteSource,
        loginRequestDomainToDataModelMapper: LoginRequestDomainToDataModelMapper,
        loginDataToDomainModelMapper: LoginDataToDomainModelMapper,
        authenticationDataToDomainExceptionMapper: AuthenticationDataToDomainExceptionMapper

    ): AuthenticationRepository = AuthenticationDataRepository(
        authenticationRemoteSource,
        loginRequestDomainToDataModelMapper,
        loginDataToDomainModelMapper,
        authenticationDataToDomainExceptionMapper

    )

    @Provides
    @Reusable
    fun providesAuthenticationDataToDomainExceptionMapper() =
        AuthenticationDataToDomainExceptionMapper()

    @Provides
    @Reusable
    fun providesLoginRequestDataToApiModelMapper() =
        LoginRequestDataToApiModelMapper()


    @Provides
    @Reusable
    fun providesLoginRequestDomainToDataModelMapper() =
        LoginRequestDomainToDataModelMapper()

    @Provides
    @Reusable
    fun providesSmartSaverTransactionDataToDomainModelMapper() =
        SmartSaverTransactionDataToDomainModelMapper()

    @Provides
    @Reusable
    fun providesUserDataApiToDataModelMapper() = UserDataApiToDataModelMapper()

    @Provides
    @Reusable
    fun providesUserDataToDomainModelMapper() = UserDataToDomainModelMapper()

    @Provides
    @Reusable
    fun providesSmartSaverTransactionApiToDataModelMapper() =
        SmartSaverTransactionApiToDataModelMapper()

    @Provides
    @Reusable
    fun providesCustomerApiToDataModelMapper(
        userDataApiToDataModelMapper: UserDataApiToDataModelMapper,
        smartSaverTransactionApiToDataModelMapper: SmartSaverTransactionApiToDataModelMapper
    ) = LoginResponseApiToDataModelMapper(
        userDataApiToDataModelMapper,
        smartSaverTransactionApiToDataModelMapper
    )

    @Provides
    @Reusable
    fun providesCustomerDataToDomainModelMapper(
        userDataToDomainModelMapper: UserDataToDomainModelMapper,
        smartSaverTransactionDataToDomainModelMapper: SmartSaverTransactionDataToDomainModelMapper
    ) = LoginDataToDomainModelMapper(
        userDataToDomainModelMapper,
        smartSaverTransactionDataToDomainModelMapper
    )
}
