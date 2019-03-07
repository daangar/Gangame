package com.daangar.commons

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.fragment_list.view.*

abstract class BaseListFragment<T>(private val itemVariableId: Int,
                                   private val itemLayoutResId: Int) : BaseFragment() {


    lateinit var  listAdapter: DataBindingRecyclerAdapter<T>


    override fun getLayoutResId(): Int = R.layout.fragment_list

    open fun getAdapter(): DataBindingRecyclerAdapter<T> {
        return DataBindingRecyclerAdapter(itemVariableId, itemLayoutResId)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listAdapter = getAdapter()

        with(view.listRV) {
            adapter = listAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }
}