package com.daangar.gangame.deals


import android.support.design.widget.Snackbar
import android.view.View
import com.daangar.commons.BaseListFragment
import com.daangar.gangame.BR
import com.daangar.gangame.Deal
import com.daangar.gangame.R
import com.daangar.gangame.data.GangameDataSource

class DealsFragment : BaseListFragment<Deal>(BR.deal, R.layout.item_deal) {


    override fun onResume() {
        super.onResume()
        showDeals()
    }

    private fun showDeals() {
        GangameDataSource
                .getDeals()
                .subscribe({ list ->
                    replaceItems(list)
                }, { error ->
                    showError(error)
                })
    }

    private fun replaceItems(list: List<Deal>) {
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
                    .setAction(R.string.label_retry, { _ : View -> showDeals() })
                    .show()
        }
    }

}