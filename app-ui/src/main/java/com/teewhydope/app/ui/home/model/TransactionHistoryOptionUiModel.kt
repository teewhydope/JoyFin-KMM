package com.teewhydope.app.ui.home.model

import androidx.annotation.ColorRes
import com.teewhydope.app.ui.R

sealed class TransactionHistoryOptionUiModel(
    @ColorRes val transactionTypeTextColor: Int
) {

    data object Credit : TransactionHistoryOptionUiModel(
        transactionTypeTextColor = R.color.green
    )

    data object Debit : TransactionHistoryOptionUiModel(
        transactionTypeTextColor = R.color.red
    )

    data object UnKnown : TransactionHistoryOptionUiModel(
        transactionTypeTextColor = R.color.transparent
    )
}