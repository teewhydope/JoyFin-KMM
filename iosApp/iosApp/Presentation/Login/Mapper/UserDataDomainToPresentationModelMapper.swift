//
//  UserDataDomainToPresentationModelMapper.swift
//  iosApp
//
//  Created by Animasahun Ibrahim on 17/03/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import IosData

class UserDataDomainToPresentationModelMapper: DomainToPresentationMapper{
    typealias T = UserDataDomainModel
    
    typealias U = UserDataPresentationModel
    
    func toPresentation(input: UserDataDomainModel) -> UserDataPresentationModel {
        
        return .init(
            firstName: input.firstName,
            smartSaverBalance: input.smartSaverBalance,
            greenSaverBalance: input.greenSaverBalance,
            fixedDepositBalance: input.fixedDepositBalance
        )
    }
    
}

