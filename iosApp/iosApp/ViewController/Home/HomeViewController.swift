//
//  HomeViewController.swift
//  iosApp
//
//  Created by Animasahun Ibrahim on 14/03/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import UIKit
import IosData

class HomeViewController: UIViewController, UITableViewDelegate, UITableViewDataSource {
    
    var uiModel: LoginResponseUiModel?
    var nairaSymbol: String = "₦"
    
    private let headerLabel: UILabel = {
        let headerLabel = UILabel()
        headerLabel.textAlignment = .center
        headerLabel.textColor = .white
        headerLabel.font = UIFont.systemFont(ofSize: 28)
        return headerLabel
    }()
    
    private let totalBalanceLabel: UILabel = {
        let totalBalanceLabel = UILabel()
        totalBalanceLabel.textColor = .white
        totalBalanceLabel.font = UIFont.systemFont(ofSize: 20)
        totalBalanceLabel.text = "Total balance"
        return totalBalanceLabel
    }()
    
    
    private let totalBalance: UILabel = {
        let totalBalance = UILabel()
        totalBalance.textColor = .white
        totalBalance.font = UIFont.boldSystemFont(ofSize: 32)
        totalBalance.numberOfLines = 0
        return totalBalance
    }()
    
    private let smartSaverLabel: UILabel = {
        let smartSaverLabel = UILabel()
        smartSaverLabel.textColor = .white
        smartSaverLabel.font = UIFont.systemFont(ofSize: 14)
        smartSaverLabel.text = "smart saver"
        return smartSaverLabel
    }()
    
    
    private let smartSaverBalance: UILabel = {
        let smartSaverBalance = UILabel()
        smartSaverBalance.textColor = .white
        smartSaverBalance.font = UIFont.systemFont(ofSize: 14)
        smartSaverBalance.numberOfLines = 0
        return smartSaverBalance
    }()
    
    private let greenSaverLabel: UILabel = {
        let greenSaverLabel = UILabel()
        greenSaverLabel.textColor = .white
        greenSaverLabel.font = UIFont.systemFont(ofSize: 14)
        greenSaverLabel.numberOfLines = 0
        greenSaverLabel.text = "green saver"
        return greenSaverLabel
    }()
    
    
    private let greenSaverBalance: UILabel = {
        let greenSaverBalance = UILabel()
        greenSaverBalance.textColor = .white
        greenSaverBalance.font = UIFont.systemFont(ofSize: 14)
        greenSaverBalance.numberOfLines = 0
        return greenSaverBalance
    }()
    
    private let fixedDepositLabel: UILabel = {
        let fixedDepositLabel = UILabel()
        fixedDepositLabel.textColor = .white
        fixedDepositLabel.font = UIFont.systemFont(ofSize: 14)
        fixedDepositLabel.text = "fixed deposit"
        return fixedDepositLabel
    }()
    
    private let fixedDepositBalance: UILabel = {
        let savedDepositBalance = UILabel()
        savedDepositBalance.textColor = .white
        savedDepositBalance.font = UIFont.systemFont(ofSize: 14)
        savedDepositBalance.numberOfLines = 0
        return savedDepositBalance
    }()
    
    private let transactionHistoryLabel: UILabel = {
        let transactionHistoryLabel = UILabel()
        transactionHistoryLabel.textColor = .white
        transactionHistoryLabel.font = UIFont.systemFont(ofSize: 28)
        transactionHistoryLabel.text = "Transaction History"
        transactionHistoryLabel.textAlignment = .center
        return transactionHistoryLabel
    }()
    
    private let cardView: UIView = {
        let cardView = UIView()
        cardView.layer.cornerRadius = 20.0
        cardView.layer.shadowColor = UIColor.gray.cgColor
        cardView.layer.shadowOffset = CGSize(width: 0.0, height: 0.0)
        cardView.layer.shadowRadius = 12.0
        cardView.layer.shadowOpacity = 0.7
        return cardView
    }()
    
    
    private let transactionTableView : UITableView = {
        let tableView = UITableView()
        tableView.backgroundColor = .black
        tableView.allowsSelection = false
        tableView.register(TransactionHistoryCell.self, forCellReuseIdentifier: TransactionHistoryCell.identifier)
        tableView.separatorColor = .white
        return tableView
        
    }()
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        navigationController?.navigationBar.barStyle = .black
        
        self.transactionTableView.delegate = self
        self.transactionTableView.dataSource = self
        
        configureUI()
        configureBalance()
        setupHeaderView()
    }
    
    override func viewDidLayoutSubviews() {
        super.viewDidLayoutSubviews()
        cardView.configureGradientLayerDefault()
    }
    
    func configureUI(){
        navigationController?.navigationBar.barStyle = .black
        navigationController?.navigationBar.isHidden = false
        
        view.addSubview(headerLabel)
        headerLabel.centerX(inView: view)
        headerLabel.anchor(top: view.safeAreaLayoutGuide.topAnchor, paddingTop: 24)
        
        view.addSubview(cardView)
        cardView.centerX(inView: view)
        cardView.anchor(top: headerLabel.bottomAnchor, left: view.leftAnchor, right: view.rightAnchor, paddingTop: 40, paddingLeft: 12, paddingRight: 12)
        
        setupCardViewItem()
        
        view.addSubview(transactionHistoryLabel)
        transactionHistoryLabel.centerX(inView: view)
        transactionHistoryLabel.anchor(top: cardView.bottomAnchor, left: view.leftAnchor, right:view.rightAnchor, paddingTop: 80)
        
        view.addSubview(transactionTableView)
        transactionTableView.centerX(inView: view)
        transactionTableView.anchor(top: transactionHistoryLabel.bottomAnchor, left: view.leftAnchor, bottom: view.safeAreaLayoutGuide.bottomAnchor, right: view.rightAnchor, paddingTop: 20, paddingLeft: 12, paddingRight: 12)
    }
    
    func configureBalance(){
        let userData = uiModel?.userData
        
        let total = NSNumber(value: (Double(userData?.smartSaverBalance ?? 0)  + Double(userData?.greenSaverBalance ?? 0) + Double(userData?.fixedDepositBalance ?? 0)))
        
        totalBalance.text = nairaSymbol.appending(total.commaSeperator())
        
        smartSaverBalance.text = nairaSymbol.appending(NSNumber(value: userData?.smartSaverBalance ?? 0).commaSeperator())
        greenSaverBalance.text = nairaSymbol.appending(NSNumber(value: userData?.greenSaverBalance ?? 0).commaSeperator())
        fixedDepositBalance.text = nairaSymbol.appending(NSNumber(value: userData?.fixedDepositBalance ?? 0).commaSeperator())
    }
    
    func setupHeaderView(){
        headerLabel.text = "Welcome back \(uiModel?.userData.firstName.description ?? "Anonymous")"
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        uiModel?.smartSaverTransactions.count ?? 0
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        guard let cell = tableView.dequeueReusableCell(withIdentifier: TransactionHistoryCell.identifier, for: indexPath) as? TransactionHistoryCell else {
            fatalError("Error dequeueing customCell")
        }
        
        let transactions = uiModel?.smartSaverTransactions ?? []
        
        let amount = nairaSymbol.appending(NSNumber(value: transactions[indexPath.row].amount).commaSeperator())
        let narration = transactions[indexPath.row].narration
        let type = transactions[indexPath.row].type
        let date = transactions[indexPath.row].dateCreated
        
        cell.configure(amountText: amount, narrationText: narration, typeText: type, dateText: date)
        
        cell.backgroundColor = .black
        return cell
    }
    
    func setupCardViewItem(){
        let totalBalanceStackView: UIStackView = UIStackView(arrangedSubviews: [totalBalanceLabel, totalBalance])
        totalBalanceStackView.applyCardStackViewConfig(view)
        
        let smartSaverStackView: UIStackView = UIStackView(arrangedSubviews: [smartSaverLabel, smartSaverBalance])
        smartSaverStackView.applyCardStackViewConfig(view)
        
        let greenSaverStackView: UIStackView = UIStackView(arrangedSubviews: [greenSaverLabel, greenSaverBalance])
        greenSaverStackView.applyCardStackViewConfig(view)
        
        let fixedDepositStackView: UIStackView = UIStackView(arrangedSubviews: [fixedDepositLabel, fixedDepositBalance])
        fixedDepositStackView.applyCardStackViewConfig(view)
        
        let accountBalancesStackView: UIStackView = UIStackView(arrangedSubviews: [smartSaverStackView, greenSaverStackView, fixedDepositStackView])
        accountBalancesStackView.distribution = .equalSpacing
        accountBalancesStackView.axis = .horizontal
        view.addSubview(accountBalancesStackView)
        accountBalancesStackView.anchor(left: cardView.leftAnchor, bottom: cardView.bottomAnchor, right: cardView.rightAnchor,paddingLeft: 10, paddingRight: 10)
        
        let cardItemStackView: UIStackView = UIStackView(arrangedSubviews: [totalBalanceStackView, SeparatorView(), accountBalancesStackView])
        cardItemStackView.axis = .vertical
        cardItemStackView.spacing = 10
        view.addSubview(cardItemStackView)
        cardItemStackView.anchor(top: cardView.topAnchor, left: cardView.leftAnchor, bottom: cardView.bottomAnchor, right: cardView.rightAnchor, paddingTop: 20, paddingBottom: 20)
    }
    
}

extension UIStackView {
    
    func applyCardStackViewConfig(_ view: UIView) {
        self.axis = .vertical
        self.spacing = 10
        self.alignment = .center
        self.centerX(inView: self)
        view.addSubview(self)
    }
}

