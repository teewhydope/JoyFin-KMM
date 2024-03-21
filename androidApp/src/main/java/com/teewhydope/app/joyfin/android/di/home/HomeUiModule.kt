package com.teewhydope.app.joyfin.android.di.home

import com.teewhydope.app.ui.home.adapter.TransactionHistoryAdapter
import com.teewhydope.app.ui.home.mapper.TransactionHistoryOptionPresentationToUiModelMapper
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object HomeUiModule {

    @Provides
    fun providesTransactionHistoryOptionPresentationToUiModelMapper() =
        TransactionHistoryOptionPresentationToUiModelMapper()

    @Provides
    @Reusable
    fun providesDeviceOwnerListAdapter(
        transactionHistoryOptionPresentationToUiModelMapper: TransactionHistoryOptionPresentationToUiModelMapper
    ) = TransactionHistoryAdapter(
        transactionHistoryOptionPresentationToUiModelMapper
    )
}



