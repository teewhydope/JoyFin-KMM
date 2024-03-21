//
//  LoginViewModel.swift
//  iosApp
//
//  Created by Animasahun Ibrahim on 14/03/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import IosData


class LoginViewModel {
    var navigate: ((_ loginResponsePresentationModel: LoginResponsePresentationModel) -> Void)?
    var isLoadin: (() -> Void)?
    var showError: ((_ error: String) -> Void)?
    var showLoader: ((_ isLoading: Bool) -> Void)?
    let loginWithEmailAndPasswordUseCase: LoginWithEmailAndPasswordUseCase
    private var useCaseExecutor: UseCaseExecutor
    private let loginResponseDomainToPresentationModelMapper: LoginResponseDomainToPresentationModelMapper
    
    init(
        loginResponseDomainToPresentationModelMapper: LoginResponseDomainToPresentationModelMapper) {
            self.loginWithEmailAndPasswordUseCase = appDIContainer.authentication.getLoginWithEmailAndPasswordUseCase
            self.useCaseExecutor = appDIContainer.useCaseExecutor
            self.loginResponseDomainToPresentationModelMapper = loginResponseDomainToPresentationModelMapper
        }
    
    convenience init() {
        self.init(
            loginResponseDomainToPresentationModelMapper: .init(
                userDataDomainToPresentationModelMapper: .init(),
                smartSaverTransactionDomainToPresentationModelMapper: .init()
            )
        )
    }
    
    func onLoginAction(email: String, password: String) {
        showLoader?(true)
        useCaseExecutor.run(
            useCase: loginWithEmailAndPasswordUseCase,
            value: LoginWithEmailAndPasswordRequestDomainModel(
                email: email,
                password: password
            ),
            onResult: { [weak self] result in
                self?.showLoader?(false)
                let presentationModel = self?.loginResponseDomainToPresentationModelMapper.toPresentation(input: result as! LoginResponseDomainModel)
                self?.navigate?(presentationModel!)
            },
            onException: { [weak self] exception in
                self?.showLoader?(false)
                guard let self = self else { return }
                self.showError?(exception.throwable.message ?? "Some went wrong")
            }
        )
    }
}
