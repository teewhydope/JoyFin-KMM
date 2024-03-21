package com.teewhydope.app.ui.login.mapper

import com.teewhydope.app.common.mapper.PresentationToUiMapper
import com.teewhydope.app.presentation.login.model.LoginResponsePresentationModel
import com.teewhydope.app.ui.login.model.LoginResponseUiModel

class LoginResponsePresentationToUiModelMapper(
    private val userDataPresentationToDomainModelMapper: UserDataPresentationToDomainModelMapper,
    private val smartSaverTransactionPresentationToUiModelMapper: SmartSaverTransactionPresentationToUiModelMapper,
) :
    PresentationToUiMapper<LoginResponsePresentationModel, LoginResponseUiModel>() {
    override fun map(input: LoginResponsePresentationModel) = LoginResponseUiModel(
        userData = userDataPresentationToDomainModelMapper.toUi(input.userData),
        smartSaverTransactions = input.smartSaverTransactions.map {
            smartSaverTransactionPresentationToUiModelMapper.toUi(it)
        }
    )
}