package com.daangar.gangame.owned

import android.support.design.widget.Snackbar
import android.view.View
import com.daangar.commons.BaseListFragment
import com.daangar.gangame.BR
import com.daangar.gangame.Deal
import com.daangar.gangame.R
import com.daangar.gangame.TopGame
import com.daangar.gangame.data.GangameDataSource

class MostOwnedFragment : BaseListFragment<TopGame>(BR.topGame, R.layout.item_top_game) {

    override fun onResume() {
        super.onResume()
        showMostOwned()
    }

    private fun showMostOwned() {
        GangameDataSource
                .getMostOwned()
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
                    .setAction(R.string.label_retry, { _ : View -> showMostOwned() })
                    .show()
        }
    }

}