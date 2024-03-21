package com.teewhydope.app.presentation.login.model

data class LoginResponsePresentationModel(
    val userData: UserDataPresentationModel,
    val smartSaverTransactions: List<SmartSaverTransactionPresentationModel>
)

data class SmartSaverTransactionPresentationModel(
    val amount: Long,
    val type: String,
    val narration: String,
    val dateCreated: String
)

data class UserDataPresentationModel(
    val firstName: String,
    val smartSaverBalance: Long,
    val greenSaverBalance: Long,
    val fixedDepositBalance: Long,
)
