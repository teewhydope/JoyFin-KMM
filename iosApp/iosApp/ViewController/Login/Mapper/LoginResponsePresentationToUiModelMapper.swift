//
//  LoginResponsePresentationToUiModelMapper.swift
//  iosApp
//
//  Created by Animasahun Ibrahim on 17/03/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation


class LoginResponsePresentationToUiModelMapper: PresentationToUiMapper {
    
    typealias T = LoginResponsePresentationModel
    
    typealias U = LoginResponseUiModel
    
    private let userDataPresentationToUiModelMapper: UserDataPresentationToUiModelMapper
    private let smartSaverTransactionPresentationToUiModelMapper: SmartSaverTransactionPresentationToUiModelMapper
    
    init(
        userDataPresentationToUiModelMapper: UserDataPresentationToUiModelMapper,
        smartSaverTransactionPresentationToUiModelMapper: SmartSaverTransactionPresentationToUiModelMapper
    ) {
        self
            .userDataPresentationToUiModelMapper = userDataPresentationToUiModelMapper
        self
            .smartSaverTransactionPresentationToUiModelMapper = smartSaverTransactionPresentationToUiModelMapper
    }
    
    func toUi(input: LoginResponsePresentationModel) -> LoginResponseUiModel {
        
        return .init(
            userData: userDataPresentationToUiModelMapper.toUi(input: input.userData),
            smartSaverTransactions: input.smartSaverTransactions.map {
                smartSaverTransactionPresentationToUiModelMapper
                    .toUi(input: $0)
            }
        )
    }
    
}

