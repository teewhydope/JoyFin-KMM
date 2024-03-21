package com.teewhydope.app.presentation.login.mapper

import com.teewhydope.app.domain.authentication.model.SmartSaverTransactionDomainModel
import com.teewhydope.app.presentation.common.mapper.DomainToPresentationMapper
import com.teewhydope.app.presentation.login.model.SmartSaverTransactionPresentationModel

class SmartSaverTransactionDomainToPresentationModelMapper :
    DomainToPresentationMapper<SmartSaverTransactionDomainModel, SmartSaverTransactionPresentationModel>() {
    override fun map(input: SmartSaverTransactionDomainModel) = SmartSaverTransactionPresentationModel(
        amount = input.amount,
        type = input.type,
        narration = input.narration,
        dateCreated = input.dateCreated,
    )
}