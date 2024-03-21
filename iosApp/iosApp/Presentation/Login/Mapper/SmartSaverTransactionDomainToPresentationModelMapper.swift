//
//  SmartSaverTransactionDomainToPresentationModelMapper.swift
//  iosApp
//
//  Created by Animasahun Ibrahim on 14/03/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import IosData

class SmartSaverTransactionDomainToPresentationModelMapper: DomainToPresentationMapper{
    typealias T = SmartSaverTransactionDomainModel
    
    typealias U = SmartSaverTransactionPresentationModel
    
    func toPresentation(input: SmartSaverTransactionDomainModel) -> SmartSaverTransactionPresentationModel {
        
        return .init(
            amount: input.amount,
            type: input.type,
            narration: input.narration,
            dateCreated: input.dateCreated
        )
    }
    
}

