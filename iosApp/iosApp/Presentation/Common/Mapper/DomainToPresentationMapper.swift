//
//  DomainToPresentationMapper.swift
//  iosApp
//
//  Created by Animasahun Ibrahim on 14/03/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation

protocol DomainToPresentationMapper {
    associatedtype T
    associatedtype U

    func toPresentation(input: T) -> U
}

