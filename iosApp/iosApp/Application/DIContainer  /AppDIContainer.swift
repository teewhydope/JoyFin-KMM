//
//  AppDIContainer.swift
//  iosApp
//
//  Created by Animasahun Ibrahim on 14/03/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import IosData

let appDIContainer = IosData.AppDIContainer(
    iOSGlobalLogger: GlobalLogger(logger: DataCommonLogger())
)
