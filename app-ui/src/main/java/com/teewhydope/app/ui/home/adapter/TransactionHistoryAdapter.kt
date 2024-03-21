package com.teewhydope.app.ui.home.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.teewhydope.app.common.adapter.BaseAdapter
import com.teewhydope.app.common.extensions.formatDecimalSeparator
import com.teewhydope.app.common.extensions.getColorCompat
import com.teewhydope.app.common.extensions.inflate
import com.teewhydope.app.ui.R
import com.teewhydope.app.ui.home.mapper.TransactionHistoryOptionPresentationToUiModelMapper
import com.teewhydope.app.ui.login.model.SmartSaverTransactionUiModel

class TransactionHistoryAdapter(
    private val transactionHistoryOptionPresentationToUiModelMapper: TransactionHistoryOptionPresentationToUiModelMapper
) :
    BaseAdapter<TransactionHistoryAdapter.ViewHolder, SmartSaverTransactionUiModel>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(parent.inflate(R.layout.transaction_list_item))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class ViewHolder(private val view: View) : BaseViewHolder(view) {

        private val transactionAmount: TextView by lazy { view.findViewById(R.id.transaction_amount) }
        private val transactionNarration: TextView by lazy { view.findViewById(R.id.transaction_narration) }
        private val transactionType: TextView by lazy { view.findViewById(R.id.transaction_type) }
        private val transactionDate: TextView by lazy { view.findViewById(R.id.transaction_date) }

        override fun bind(item: SmartSaverTransactionUiModel) {
            transactionAmount.text =
                view.context.getString(
                    R.string.amount_naira_symbol,
                    item.amount.formatDecimalSeparator()
                )
            transactionNarration.text = item.narration
            transactionType.text = item.type
            transactionDate.text = item.dateCreated

            val transactionTypeColor =
                transactionHistoryOptionPresentationToUiModelMapper
                    .toUi(transactionType.text.toString()).transactionTypeTextColor
            transactionType.setTextColor(getColorCompat(transactionTypeColor))
        }
    }
}