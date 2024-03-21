package com.teewhydope.app.presentation.login.mapper

import com.teewhydope.app.domain.authentication.model.LoginResponseDomainModel
import com.teewhydope.app.presentation.common.mapper.DomainToPresentationMapper
import com.teewhydope.app.presentation.login.model.LoginResponsePresentationModel

class LoginResponseDomainToPresentationModelMapper(
    private val userDataDomainToPresentationModelMapper: UserDataDomainToPresentationModelMapper,
    private val smartSaverTransactionDomainToPresentationModelMapper: SmartSaverTransactionDomainToPresentationModelMapper,
) :
    DomainToPresentationMapper<LoginResponseDomainModel, LoginResponsePresentationModel>() {
    override fun map(input: LoginResponseDomainModel) = LoginResponsePresentationModel(
        userData = userDataDomainToPresentationModelMapper.toPresentation(input.userData),
        smartSaverTransactions = input.smartSaverTransactions.map {
            smartSaverTransactionDomainToPresentationModelMapper.toPresentation(it)
        }
    )
}