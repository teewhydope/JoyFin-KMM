//
//  SeparatorView.swift
//  iosApp
//
//  Created by Animasahun Ibrahim on 17/03/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import UIKit

class SeparatorView: UIView {
    
    init() {
        super.init(frame: .zero)
        setUp()
    }
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        setUp()
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    private func setUp() {
        backgroundColor = .white
    }
    
    override var intrinsicContentSize: CGSize {
        return CGSize(width: UIView.noIntrinsicMetric, height: 1 / UIScreen.main.scale)
    }
}

