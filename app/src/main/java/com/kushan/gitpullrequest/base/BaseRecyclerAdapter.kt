package com.kushan.gitpullrequest.base

import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import java.util.ArrayList
import androidx.databinding.ViewDataBinding

abstract class BaseRecyclerAdapter<T> constructor(private val listItems: MutableList<T> = ArrayList()) : RecyclerView.Adapter<BaseRecyclerAdapter.ViewHolder<T>>() {

    protected  lateinit var layouInflater : LayoutInflater

    override fun onBindViewHolder(holder: ViewHolder<T>, position: Int) {
        holder.bind(listItems[position])
        holder.dataBinding.executePendingBindings()
    }

    override fun getItemCount(): Int {
        return listItems.size
    }

    /**
     * Replace listitems with new list
     * @param listItems needs to replace
     */
    fun replaceAll(listItems: List<T>) {
        this.listItems.clear()
        this.listItems.addAll(listItems)
        notifyDataSetChanged()
    }

    abstract class ViewHolder<T>(val dataBinding: ViewDataBinding) : RecyclerView.ViewHolder(dataBinding.getRoot()) {
        abstract fun bind(displayData : T)
    }
}
