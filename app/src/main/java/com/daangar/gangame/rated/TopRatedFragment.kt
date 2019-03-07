package com.daangar.gangame.rated


import android.support.design.widget.Snackbar
import android.view.View
import com.daangar.commons.BaseListFragment
import com.daangar.commons.DataBindingRecyclerAdapter
import com.daangar.gangame.BR
import com.daangar.gangame.R
import com.daangar.gangame.TopGame
import com.daangar.gangame.data.GangameDataSource


class TopRatedFragment : BaseListFragment<TopGame>(0,0) {

    override fun getAdapter(): DataBindingRecyclerAdapter<TopGame> {
        return DataBindingRecyclerAdapter(BR.topGame, R.layout.item_top_game)
    }

    override fun onResume() {
        super.onResume()
        showTopRated()
    }

    private fun showTopRated() {
        GangameDataSource
                .getTopRated()
                .subscribe({ list ->
                    replaceItems(list)
                }, { error ->
                    showError(error)
                })
    }

    private fun replaceItems(list: List<TopGame>) {
        with(listAdapter)
        {
            items.clear()
            items.addAll(list)
            notifyDataSetChanged()
        }
    }

    private fun showError(error: Throwable) {
        error.printStackTrace()

        view?.let {
            Snackbar.make(it, R.string.error_request, Snackbar.LENGTH_LONG)
                    .setAction(R.string.label_retry, { _ : View -> showTopRated() })
                    .show()
        }
    }

}