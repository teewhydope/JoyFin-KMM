package com.teewhydope.app.data.authentication.mapper

import com.teewhydope.app.data.authentication.model.LoginResponseDataModel
import com.teewhydope.app.data.common.mapper.DataToDomainMapper
import com.teewhydope.app.domain.authentication.model.LoginResponseDomainModel

class LoginDataToDomainModelMapper(
    private val userDataToDomainModelMapper: UserDataToDomainModelMapper,
    private val smartSaverTransactionDataToDomainModelMapper: SmartSaverTransactionDataToDomainModelMapper
) : DataToDomainMapper<LoginResponseDataModel, LoginResponseDomainModel>() {
    override fun map(input: LoginResponseDataModel) = LoginResponseDomainModel(
        userData = userDataToDomainModelMapper.toDomain(input.userData),
        smartSaverTransactions = input.smartSaverTransactions.map { transactions ->
            smartSaverTransactionDataToDomainModelMapper.toData(transactions)
        }
    )
}