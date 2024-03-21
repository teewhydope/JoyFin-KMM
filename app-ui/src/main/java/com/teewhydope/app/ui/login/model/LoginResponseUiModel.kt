package com.teewhydope.app.ui.login.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LoginResponseUiModel(
    val userData: UserDataUiModel,
    val smartSaverTransactions: List<SmartSaverTransactionUiModel>
) : Parcelable

@Parcelize
data class SmartSaverTransactionUiModel(
    val amount: Long,
    val type: String,
    val narration: String,
    val dateCreated: String
) : Parcelable

@Parcelize
data class UserDataUiModel(
    val firstName: String,
    val smartSaverBalance: Long,
    val greenSaverBalance: Long,
    val fixedDepositBalance: Long,
) : Parcelable