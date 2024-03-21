package com.teewhydope.app.joyfin.android.di.network

import com.teewhydope.app.data.common.network.NetworkClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun providesNetworkClient(): HttpClient {
        return NetworkClient().build()
    }
}
