package com.teewhydope.app.data.authentication.mapper

import com.teewhydope.app.data.common.mapper.ApiToDataMapper
import com.teewhydope.app.data.authentication.model.SmartSaverTransactionApiModel
import com.teewhydope.app.data.authentication.model.SmartSaverTransactionDataModel

class SmartSaverTransactionApiToDataModelMapper :
    ApiToDataMapper<SmartSaverTransactionApiModel, SmartSaverTransactionDataModel>() {
    override fun map(input: SmartSaverTransactionApiModel) = SmartSaverTransactionDataModel(
        amount = input.amount,
        type = input.type,
        narration = input.narration,
        dateCreated = input.dateCreated,
    )
}