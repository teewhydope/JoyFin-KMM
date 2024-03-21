package com.teewhydope.app.data.authentication.mapper

import com.teewhydope.app.data.common.mapper.ApiToDataMapper
import com.teewhydope.app.data.authentication.model.SmartSaverTransactionDataModel
import com.teewhydope.app.domain.authentication.model.SmartSaverTransactionDomainModel

class SmartSaverTransactionDataToDomainModelMapper :
    ApiToDataMapper<SmartSaverTransactionDataModel, SmartSaverTransactionDomainModel>() {
    override fun map(input: SmartSaverTransactionDataModel) = SmartSaverTransactionDomainModel(
        amount = input.amount,
        type = input.type,
        narration = input.narration,
        dateCreated = input.dateCreated,
    )
}