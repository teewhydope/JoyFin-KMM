//
//  UserDataPresentationToUiModelMapper.swift
//  iosApp
//
//  Created by Animasahun Ibrahim on 17/03/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation

class UserDataPresentationToUiModelMapper: PresentationToUiMapper{
    typealias T = UserDataPresentationModel
    
    typealias U = UserDataUiModel
    
    func toUi(input: UserDataPresentationModel) -> UserDataUiModel {
        
        return .init(
            firstName: input.firstName,
            smartSaverBalance: input.smartSaverBalance,
            greenSaverBalance: input.greenSaverBalance,
            fixedDepositBalance: input.fixedDepositBalance
        )
    }
    
}
