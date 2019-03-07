package com.daangar.commons


import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.View

class DataBindingViewHolder<MODEL>(private val itemVariableId: Int,
                                   private val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bindItem(item: MODEL) {
        binding.setVariable(itemVariableId, item)
        binding.executePendingBindings()
    }

}