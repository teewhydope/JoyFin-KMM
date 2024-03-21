package com.teewhydope.app.ui.login.mapper

import com.teewhydope.app.common.mapper.PresentationToUiMapper
import com.teewhydope.app.presentation.login.model.SmartSaverTransactionPresentationModel
import com.teewhydope.app.ui.login.model.SmartSaverTransactionUiModel

class SmartSaverTransactionPresentationToUiModelMapper :
    PresentationToUiMapper<SmartSaverTransactionPresentationModel, SmartSaverTransactionUiModel>() {
    override fun map(input: SmartSaverTransactionPresentationModel) =
        SmartSaverTransactionUiModel(
            amount = input.amount,
            type = input.type,
            narration = input.narration,
            dateCreated = input.dateCreated,
        )
}