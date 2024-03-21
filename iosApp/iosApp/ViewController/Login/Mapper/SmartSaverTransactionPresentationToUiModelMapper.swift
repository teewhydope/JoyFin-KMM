//
//  SmartSaverTransactionPresentationToUiModelMapper.swift
//  iosApp
//
//  Created by Animasahun Ibrahim on 17/03/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation

class SmartSaverTransactionPresentationToUiModelMapper: PresentationToUiMapper{
    typealias T = SmartSaverTransactionPresentationModel
    
    typealias U = SmartSaverTransactionUiModel
    
    func toUi(input: SmartSaverTransactionPresentationModel) -> SmartSaverTransactionUiModel {
        
        return .init(
            amount: input.amount,
            type: input.type,
            narration: input.narration,
            dateCreated: input.dateCreated
        )
    }
    
}
