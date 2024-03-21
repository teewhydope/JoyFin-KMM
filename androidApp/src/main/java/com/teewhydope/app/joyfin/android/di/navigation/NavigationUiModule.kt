package com.teewhydope.app.joyfin.android.di.navigation

import android.content.res.Resources
import com.teewhydope.app.common.navigation.mapper.GlobalDestinationMapper
import com.teewhydope.app.common.navigation.mapper.UiDestinationMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object NavigationUiModule {

    @Provides
    fun providesGlobalDestinationMapper() =
        GlobalDestinationMapper()

    @Provides
    fun providesUiDestinationMapper(): UiDestinationMapper =
        GlobalDestinationMapper()
}