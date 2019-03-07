package com.daangar.gangame.data


import com.daangar.gangame.Deal
import com.daangar.gangame.TopGame
import com.daangar.gangame.sdk.GangameApiService
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


object GangameDataSource {

    val apiService = GangameApiService()

    fun getDeals(): Observable<ArrayList<Deal>> {
        return apiService.apiClient
                .getDealsObservable()
                .map { listDeal ->
                    val deals = listDeal.map { deal -> DealMapper.fromSdk(deal) }
                    val arrayList = arrayListOf<Deal>()
                    arrayList.addAll(deals)
                    arrayList

                }.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
    }


    fun getTopRated(): Observable<ArrayList<TopGame>> {
        return apiService.apiClient
                .getTopRatedGamesObservable()
                .map { listGames ->
                    val games = listGames.mapIndexed { index, game ->
                        TopGameMapper.fromSdk(game, index + 1)
                    }
                    val arrayList = arrayListOf<TopGame>()
                    arrayList.addAll(games)
                    arrayList

                }.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
    }

    fun getMostOwned(): Observable<ArrayList<TopGame>> {
        return apiService.apiClient
                .getMostOwnedGamesObservable()
                .map { listGames ->
                    val games = listGames.mapIndexed { index, game ->
                        TopGameMapper.fromSdk(game, index + 1)
                    }
                    val arrayList = arrayListOf<TopGame>()
                    arrayList.addAll(games)
                    arrayList

                }.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
    }

}