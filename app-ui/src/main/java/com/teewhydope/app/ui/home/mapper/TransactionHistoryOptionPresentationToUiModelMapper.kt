package com.teewhydope.app.ui.home.mapper

import com.teewhydope.app.common.mapper.PresentationToUiMapper
import com.teewhydope.app.ui.home.model.TransactionHistoryOptionUiModel
import com.teewhydope.app.ui.home.model.TransactionHistoryOptionUiModel.Credit
import com.teewhydope.app.ui.home.model.TransactionHistoryOptionUiModel.Debit
import com.teewhydope.app.ui.home.model.TransactionHistoryOptionUiModel.UnKnown

class TransactionHistoryOptionPresentationToUiModelMapper :
    PresentationToUiMapper<String, TransactionHistoryOptionUiModel>() {
    override fun map(input: String) =
        when (input) {
            "credit" -> Credit
            "debit" -> Debit
            else -> UnKnown
        }
}