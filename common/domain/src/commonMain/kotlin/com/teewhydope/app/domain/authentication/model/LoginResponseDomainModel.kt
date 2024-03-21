package com.teewhydope.app.domain.authentication.model

data class LoginResponseDomainModel(
    val userData: UserDataDomainModel,
    val smartSaverTransactions: List<SmartSaverTransactionDomainModel>
)

data class SmartSaverTransactionDomainModel(
    val amount: Long,
    val type: String,
    val narration: String,
    val dateCreated: String
)

data class UserDataDomainModel(
    val firstName: String,
    val smartSaverBalance: Long,
    val greenSaverBalance: Long,
    val fixedDepositBalance: Long,
)
