//
//  TransactionHistoryCell.swift
//  iosApp
//
//  Created by Animasahun Ibrahim on 14/03/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import UIKit

class TransactionHistoryCell: UITableViewCell {
    static let identifier = "TransactionHistoryCell"
    
    private let amountText: UILabel = {
        let label = UILabel()
        label.textColor = .white
        label.font = .systemFont(ofSize: 16,weight: .medium)
        return label
    }()
    
    private let narrationText: UILabel = {
        let label = UILabel()
        label.textColor = .white
        label.font = .systemFont(ofSize: 16,weight: .medium)
        return label
    }()
    
    private let typeText: UILabel = {
        let label = UILabel()
        label.textColor = .white
        label.font = .systemFont(ofSize: 16,weight: .medium)
        return label
    }()
    
    private let dateText: UILabel = {
        let label = UILabel()
        label.textColor = .white
        label.font = .systemFont(ofSize: 16,weight: .medium)
        return label
    }()
    
    public func configure(amountText: String, narrationText: String, typeText: String, dateText: String) {
        self.amountText.text = amountText
        self.narrationText.text = narrationText
        self.typeText.text = typeText
        self.dateText.text = dateText
        
    }
    
    override init(style: UITableViewCell.CellStyle, reuseIdentifier: String?) {
        super.init(style: style, reuseIdentifier: reuseIdentifier)
        setupUI()
    }
    
    private func setupUI() {
        self.contentView.addSubview(amountText)
        self.contentView.addSubview(narrationText)
        self.contentView.addSubview(typeText)
        self.contentView.addSubview(dateText)        
        
        amountText.anchor(top: contentView.topAnchor, left: contentView.leftAnchor,
                          paddingTop: 10, paddingLeft: 12, paddingRight: 12)
        narrationText.anchor(top: amountText.bottomAnchor, left: contentView.leftAnchor, bottom: contentView.bottomAnchor,
                             paddingTop: 20, paddingLeft: 12, paddingBottom: 10, paddingRight: 12)
        typeText.anchor(top: contentView.topAnchor, right: contentView.rightAnchor,
                        paddingTop: 10, paddingLeft: 12, paddingRight: 12)
        dateText.anchor(top: typeText.bottomAnchor, bottom: contentView.bottomAnchor, right: contentView.rightAnchor,
                        paddingTop: 20, paddingLeft: 12, paddingBottom: 10, paddingRight: 12)
        
        
    }
    
    override func prepareForReuse() {
        super.prepareForReuse()
        
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
}

