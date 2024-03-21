package com.teewhydope.app.ui.home

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.teewhydope.app.common.extensions.formatDecimalSeparator
import com.teewhydope.app.common.fragment.BaseFragment
import com.teewhydope.app.common.navigation.mapper.UiDestinationMapper
import com.teewhydope.app.presentation.common.internal.ViewState
import com.teewhydope.app.presentation.common.notification.PresentationNotification
import com.teewhydope.app.presentation.common.viewmodel.DoNothingViewModel
import com.teewhydope.app.ui.R
import com.teewhydope.app.ui.home.adapter.TransactionHistoryAdapter
import com.teewhydope.app.ui.home.widgets.HomeAccountItemView
import com.teewhydope.app.ui.login.model.SmartSaverTransactionUiModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : BaseFragment<ViewState, PresentationNotification>() {

    private val arguments: HomeFragmentArgs by navArgs()

    override val layout: Int = R.layout.fragment_home

    @Inject
    override lateinit var destinationMapper: UiDestinationMapper

    override val viewModel: DoNothingViewModel = DoNothingViewModel()

    @Inject
    lateinit var transactionsAdapter: TransactionHistoryAdapter

    private val transactionListView: RecyclerView
        get() = requireView().findViewById(R.id.transaction_history_list_view)

    private val smartSaverView
        get() = requireView().findViewById<HomeAccountItemView>(R.id.smart_saver_view)

    private val greenSaverView
        get() = requireView().findViewById<HomeAccountItemView>(R.id.green_saver_view)

    private val fixedDepositView
        get() = requireView().findViewById<HomeAccountItemView>(R.id.fixed_deposit_view)

    private val totalBalanceView
        get() = requireView().findViewById<TextView>(R.id.total_balance_value)

    private val welcomeLabel
        get() = requireView().findViewById<TextView>(R.id.home_bar_title)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupCardView()
        setupHeaderView()
        bindUserTransactions(arguments.response.smartSaverTransactions)
    }

    private fun setupCardView() {
        val userData = arguments.response.userData
        smartSaverView.accountBalanceText =
            getString(
                R.string.amount_naira_symbol,
                userData.smartSaverBalance.formatDecimalSeparator()
            )


        greenSaverView.accountBalanceText =
            getString(
                R.string.amount_naira_symbol,
                userData.greenSaverBalance.formatDecimalSeparator()
            )

        fixedDepositView.accountBalanceText =
            getString(
                R.string.amount_naira_symbol,
                userData.fixedDepositBalance.formatDecimalSeparator()
            )

        val totalBalance =
            (userData.smartSaverBalance)
                .plus(userData.greenSaverBalance)
                .plus(userData.fixedDepositBalance).formatDecimalSeparator()

        totalBalanceView.text =
            getString(R.string.amount_naira_symbol, totalBalance)
    }

    private fun bindUserTransactions(transactions: List<SmartSaverTransactionUiModel>) {
        transactionListView.layoutManager = LinearLayoutManager(
            context, LinearLayoutManager.VERTICAL, false
        )
        transactionsAdapter.items = transactions
        transactionListView.adapter = transactionsAdapter
    }

    private fun setupHeaderView() {
        welcomeLabel.text =
            getString(R.string.home_welcome_label, arguments.response.userData.firstName)
    }
}