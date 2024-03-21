//
//  PresentationToUiMapper.swift
//  iosApp
//
//  Created by Animasahun Ibrahim on 17/03/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation

protocol PresentationToUiMapper {
    associatedtype T
    associatedtype U

    func toUi(input: T) -> U
}

