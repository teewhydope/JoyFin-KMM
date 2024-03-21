//
//  LoginResponsePresentationModel.swift
//  iosApp
//
//  Created by Animasahun Ibrahim on 17/03/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation

struct LoginResponsePresentationModel{
    let userData: UserDataPresentationModel
    let smartSaverTransactions: [SmartSaverTransactionPresentationModel]
}

struct SmartSaverTransactionPresentationModel{
    let amount: Int64
    let type: String
    let narration: String
    let dateCreated: String
}

struct UserDataPresentationModel {
    let firstName: String
    let smartSaverBalance: Int64
    let greenSaverBalance: Int64
    let fixedDepositBalance: Int64
}

