//
//  AppDelegate+RootViewController.swift
//  iosApp
//
//  Created by Animasahun Ibrahim on 14/03/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import UIKit

extension AppDelegate {
    func setRootViewController() {
        let navigationController = UINavigationController(rootViewController: LoginViewController())
        navigationController.setNavigationBarHidden(true, animated: false)
        window = UIWindow(frame: UIScreen.main.bounds)
        window?.rootViewController = navigationController
        window?.makeKeyAndVisible()
    }
}
