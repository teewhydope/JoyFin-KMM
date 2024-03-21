package com.teewhydope.app.data.authentication.mapper

import com.teewhydope.app.data.common.mapper.ApiToDataMapper
import com.teewhydope.app.data.authentication.model.LoginResponseApiModel
import com.teewhydope.app.data.authentication.model.LoginResponseDataModel

class LoginResponseApiToDataModelMapper(
    private val userDataApiToDataModelMapper: UserDataApiToDataModelMapper,
    private val smartSaverTransactionApiToDataModelMapper: SmartSaverTransactionApiToDataModelMapper
) : ApiToDataMapper<LoginResponseApiModel, LoginResponseDataModel>() {
    override fun map(input: LoginResponseApiModel) = LoginResponseDataModel(
        userData = userDataApiToDataModelMapper.toData(input.userData),
        smartSaverTransactions = input.smartSaverTransactions.map { transactions ->
            smartSaverTransactionApiToDataModelMapper.toData(transactions)

        }
    )
}