//
//  LoginResponseDomainToPresentationModelMapper.swift
//  iosApp
//
//  Created by Animasahun Ibrahim on 17/03/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import IosData

class LoginResponseDomainToPresentationModelMapper: DomainToPresentationMapper{
    typealias T = LoginResponseDomainModel
    
    typealias U = LoginResponsePresentationModel
    
    private let userDataDomainToPresentationModelMapper: UserDataDomainToPresentationModelMapper
    private let smartSaverTransactionDomainToPresentationModelMapper: SmartSaverTransactionDomainToPresentationModelMapper
    
    init(
        userDataDomainToPresentationModelMapper: UserDataDomainToPresentationModelMapper,
        smartSaverTransactionDomainToPresentationModelMapper: SmartSaverTransactionDomainToPresentationModelMapper
    ) {
        self
            .userDataDomainToPresentationModelMapper = userDataDomainToPresentationModelMapper
        self
            .smartSaverTransactionDomainToPresentationModelMapper = smartSaverTransactionDomainToPresentationModelMapper
    }
    
    func toPresentation(input: LoginResponseDomainModel) -> LoginResponsePresentationModel {
        
        return .init(
            userData: userDataDomainToPresentationModelMapper.toPresentation(input: input.userData),
            smartSaverTransactions: input.smartSaverTransactions.map {
                smartSaverTransactionDomainToPresentationModelMapper
                    .toPresentation(input: $0)
            }
        )
    }
    
}
