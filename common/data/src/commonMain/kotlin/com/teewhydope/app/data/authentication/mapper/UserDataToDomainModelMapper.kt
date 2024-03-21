package com.teewhydope.app.data.authentication.mapper

import com.teewhydope.app.data.authentication.model.UserDataModel
import com.teewhydope.app.data.common.mapper.DataToDomainMapper
import com.teewhydope.app.domain.authentication.model.UserDataDomainModel

class UserDataToDomainModelMapper : DataToDomainMapper<UserDataModel, UserDataDomainModel>() {
    override fun map(input: UserDataModel) = UserDataDomainModel(
        firstName = input.firstName,
        smartSaverBalance = input.smartSaverBalance,
        greenSaverBalance = input.greenSaverBalance,
        fixedDepositBalance = input.fixedDepositBalance,
    )
}