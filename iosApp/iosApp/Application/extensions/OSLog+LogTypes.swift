//
//  OSLog+LogTypes.swift
//  iosApp
//
//  Created by Animasahun Ibrahim on 14/03/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import os.log

extension OSLog {
    private static var subsystem = Bundle.main.bundleIdentifier!

    /// Logs the use case cycles execute.
    static let useCase = OSLog(subsystem: subsystem, category: "usecase")

    static let viewController = OSLog(subsystem: subsystem, category: "ViewController")
}
