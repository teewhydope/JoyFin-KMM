package com.teewhydope.app.joyfin.android.di.authentication

import com.teewhydope.app.presentation.login.mapper.LoginResponseDomainToPresentationModelMapper
import com.teewhydope.app.presentation.login.mapper.SmartSaverTransactionDomainToPresentationModelMapper
import com.teewhydope.app.presentation.login.mapper.UserDataDomainToPresentationModelMapper
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object AuthenticationPresentationModule {
    @Provides
    @Reusable
    fun providesLoginResponseDomainToPresentationModelMapper(
        userDataDomainToPresentationModelMapper: UserDataDomainToPresentationModelMapper,
        smartSaverTransactionDomainToPresentationModelMapper: SmartSaverTransactionDomainToPresentationModelMapper,
    ) = LoginResponseDomainToPresentationModelMapper(
        userDataDomainToPresentationModelMapper,
        smartSaverTransactionDomainToPresentationModelMapper,
    )

    @Provides
    @Reusable
    fun providesUserDataDomainToPresentationModelMapper() =
        UserDataDomainToPresentationModelMapper()

    @Provides
    @Reusable
    fun providesSmartSaverTransactionDomainToPresentationModelMapper() =
        SmartSaverTransactionDomainToPresentationModelMapper()
}