package com.teewhydope.app.joyfin.android.di.authentication

import com.teewhydope.app.common.navigation.mapper.GlobalDestinationMapper
import com.teewhydope.app.ui.login.mapper.LoginPresentationToUiDestinationMapper
import com.teewhydope.app.ui.login.mapper.LoginResponsePresentationToUiModelMapper
import com.teewhydope.app.ui.login.mapper.SmartSaverTransactionPresentationToUiModelMapper
import com.teewhydope.app.ui.login.mapper.UserDataPresentationToDomainModelMapper
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AuthenticationUiModule {
    @Provides
    @Reusable
    fun providesLoginResponsePresentationToUiModelMapper(
        userDataPresentationToDomainModelMapper: UserDataPresentationToDomainModelMapper,
        smartSaverTransactionPresentationToUiModelMapper: SmartSaverTransactionPresentationToUiModelMapper,
    ) = LoginResponsePresentationToUiModelMapper(
        userDataPresentationToDomainModelMapper,
        smartSaverTransactionPresentationToUiModelMapper,
    )

    @Provides
    @Reusable
    fun providesUserDataPresentationToDomainModelMapper() =
        UserDataPresentationToDomainModelMapper()

    @Provides
    @Reusable
    fun providesSmartSaverTransactionPresentationToUiModelMapper() =
        SmartSaverTransactionPresentationToUiModelMapper()

    @Provides
    @Reusable
    fun providesLoginPresentationToUiDestinationMapper(
        globalDestinationMapper: GlobalDestinationMapper,
        loginResponsePresentationToUiModelMapper: LoginResponsePresentationToUiModelMapper
    ) =
        LoginPresentationToUiDestinationMapper(
            loginResponsePresentationToUiModelMapper,
            globalDestinationMapper
        )
}