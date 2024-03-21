package com.teewhydope.app.common.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<VH : BaseAdapter<VH, E>.BaseViewHolder, E>(items: List<E> = emptyList()) :
    RecyclerView.Adapter<VH>() {

    var itemClickListener: (E) -> Unit = {}

    private val _items: MutableList<E> = ArrayList(items)
    var items: List<E>
        get() = _items
        set(values) {
            _items.clear()
            _items.addAll(values)
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(items[position])
    }

    abstract inner class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener {
                itemClickListener(items[bindingAdapterPosition])
                onItemClick(items[bindingAdapterPosition])
            }
        }

        abstract fun bind(item: E)
        open fun onItemClick(item: E) {}
    }

    fun <V : View, T : RecyclerView.ViewHolder> V.initParamsWithViewHolder(viewHolderType: (V: View) -> T): T {
        this.layoutParams = RecyclerView.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        return viewHolderType.invoke(this).apply { setIsRecyclable(false) }
    }
}