package com.teewhydope.app.joyfin.android.di.architecture

import com.teewhydope.app.presentation.common.internal.exception.GeneralDomainToPresentationExceptionMapper
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object PresentationModule {

    @Provides
    @Reusable
    fun providesGeneralDomainToPresentationExceptionMapper() =
        GeneralDomainToPresentationExceptionMapper()
}