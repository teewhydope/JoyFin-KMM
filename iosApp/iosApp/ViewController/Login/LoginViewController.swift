//
//  LoginViewController.swift
//  iosApp
//
//  Created by Animasahun Ibrahim on 14/03/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import UIKit


class LoginViewController: UIViewController {
    let viewmodel = LoginViewModel()
    let loginResponsePresentationToUiModelMapper: LoginResponsePresentationToUiModelMapper =
        .init(userDataPresentationToUiModelMapper: .init(), smartSaverTransactionPresentationToUiModelMapper: .init())
    
    
    private let titleLabel: UILabel = {
        let titleLabel = UILabel()
        titleLabel.text = "Login"
        titleLabel.textColor = .white
        titleLabel.font = UIFont.systemFont(ofSize: 40)
        return titleLabel
    }()
    
    
    
    private let emailTextField: CustomTextField = {
        let textField = CustomTextField(placeHolder: "Email address")
        textField.keyboardType = .emailAddress
        return textField
    }()
    
    
    
    private let passwordTextField: CustomTextField = {
        let textField = CustomTextField(placeHolder: "Password")
        textField.isSecureTextEntry = true
        return textField
    }()
    
    let activityIndicator: UIActivityIndicatorView = {
        let activityIndicator = UIActivityIndicatorView(style: .medium)
        activityIndicator.translatesAutoresizingMaskIntoConstraints = false
        activityIndicator.hidesWhenStopped = true
        return activityIndicator
    }()
    
    
    
    private lazy var loginButton: CustomButton = {
        let button = CustomButton(title: "Login")
        button.addTarget(
            self,
            action: #selector(loginButtonPressed),
            for: .touchUpInside)
        return button
    }()
    
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        configureUI()
        bind()
    }
    
    
    
    
    @objc func loginButtonPressed(sender: UIButton!) {
        viewmodel.onLoginAction(email: emailTextField.text!, password: passwordTextField.text!)
    }
    
    
    func bind() {
        viewmodel.navigate = { result in
            DispatchQueue.main.async {
                let homeViewController = HomeViewController()
                homeViewController.uiModel = self.loginResponsePresentationToUiModelMapper.toUi(input: result)
                self.navigationController?.pushViewController(homeViewController, animated: false)
            }
        }
        viewmodel.showError = { result in
            self.showMessage(withTitle: "An error occured", message: result)
        }
        viewmodel.showLoader = { isLoading in
            self.showLoader(isLoading: isLoading)
        }
        
    }
    
    private func showLoader(isLoading: Bool = false) {
        if(isLoading){
            activityIndicator.isHidden = false
            activityIndicator.startAnimating()
            loginButton.isHidden = true
        }else{
            activityIndicator.stopAnimating()
            activityIndicator.isHidden = true
            loginButton.isHidden = false
        }
    }
    
    
    func configureUI(){
        navigationController?.navigationBar.barStyle = .black
        navigationController?.navigationBar.isHidden = true
        
        view.addSubview(titleLabel)
        titleLabel.centerX(inView: view)
        titleLabel.anchor(top: view.safeAreaLayoutGuide.topAnchor, paddingTop: 32)
        
        let stackView: UIStackView = UIStackView(arrangedSubviews: [emailTextField, passwordTextField, loginButton, activityIndicator])
        stackView.axis = .vertical
        stackView.spacing = 40
        
        view.addSubview(stackView)
        stackView.centerX(inView: view)
        stackView.anchor(top: titleLabel.bottomAnchor, left: view.leftAnchor, right: view.rightAnchor, paddingTop: 60, paddingLeft: 32, paddingRight: 32)
    }
    
}
