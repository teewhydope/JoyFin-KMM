package com.teewhydope.app.joyfin.android.di.architecture

import com.teewhydope.app.common.mapper.GeneralPresentationToUiExceptionMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
object UiModule {

    @Provides
    fun providesGeneralPresentationToUiExceptionMapper() =
        GeneralPresentationToUiExceptionMapper()
}