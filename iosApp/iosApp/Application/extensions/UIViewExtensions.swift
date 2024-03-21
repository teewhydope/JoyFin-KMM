//
//  UIViewExtensions.swift
//  iosApp
//
//  Created by Animasahun Ibrahim on 17/03/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import UIKit


extension UIView{
    
    func configureGradientLayerDefault() {
        let gradientLayer = CAGradientLayer()
        gradientLayer.colors = [UIColor.systemPurple.cgColor, UIColor.systemBlue.cgColor]
        gradientLayer.startPoint = CGPoint(x: 0.5, y: 1.0)
        gradientLayer.endPoint = CGPoint(x: 0.5, y: 0.0)
        gradientLayer.locations = [0, 1]
        gradientLayer.frame = bounds
        gradientLayer.cornerRadius = 20
        layer.insertSublayer(gradientLayer, at: 0)
    }
    
    
    func configureGradientLayer(){
        func setGradientBackground(colorTop: UIColor, colorBottom: UIColor) {
            let gradientLayer = CAGradientLayer()
            gradientLayer.colors = [colorBottom.cgColor, colorTop.cgColor]
            gradientLayer.startPoint = CGPoint(x: 0.5, y: 1.0)
            gradientLayer.endPoint = CGPoint(x: 0.5, y: 0.0)
            gradientLayer.locations = [0, 1]
            gradientLayer.frame = bounds
            
            layer.insertSublayer(gradientLayer, at: 0)
        }
    }
    
}

