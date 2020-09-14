package com.kuba.bunqrecruitmentapp.ui.payments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.kuba.bunqrecruitmentapp.R
import com.kuba.bunqrecruitmentapp.api.models.payment.Payment
import com.kuba.bunqrecruitmentapp.databinding.ItemFetchingBinding
import com.kuba.bunqrecruitmentapp.databinding.ItemPaymentBinding

class PaymentsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val paymentsList = ArrayList<Payment>()
    var fetchedAll = false
        set(value) {
            field = value
            notifyItemChanged(paymentsList.size)
        }

    fun setData(newData: List<Payment>) {
        val diffResult = DiffUtil.calculateDiff(PaymentDiffUtilCallback(paymentsList, newData))
        paymentsList.addAll(newData)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun getItemViewType(position: Int): Int {
        return if (position < paymentsList.size) {
            R.layout.item_payment
        } else {
            R.layout.item_fetching
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return if (viewType == R.layout.item_payment) {
            val binding = ItemPaymentBinding.inflate(inflater, parent, false)
            PaymentViewHolder(binding)
        } else {
            val binding = ItemFetchingBinding.inflate(inflater, parent, false)
            FetchingViewHolder(binding)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is PaymentViewHolder) {
            val model = paymentsList[position]
            holder.bind(model)
        } else if (holder is FetchingViewHolder) {
            holder.itemView.visibility = if (fetchedAll) View.GONE else View.VISIBLE
        }
    }

    override fun getItemCount() = paymentsList.size + 1

    class PaymentViewHolder(private val binding: ItemPaymentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(model: Payment) {
            binding.amountTV.text =
                String.format("%s %s", model.amount.value, model.amount.currency)
            binding.nameTV.text = model.alias.displayName
            binding.descTV.text = model.description
        }

    }

    class FetchingViewHolder(private val binding: ItemFetchingBinding) :
        RecyclerView.ViewHolder(binding.root)

    class PaymentDiffUtilCallback(
        private val oldList: List<Payment>,
        private val newList: List<Payment>
    ) :
        DiffUtil.Callback() {

        override fun getOldListSize() = oldList.size

        override fun getNewListSize() = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            oldList[oldItemPosition].id == newList[newItemPosition].id

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            oldList[oldItemPosition] == newList[newItemPosition]
    }
}