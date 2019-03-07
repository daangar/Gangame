package com.daangar.gangame.data

import com.daangar.gangame.Deal

object DealMapper {

    fun fromSdk(deal: com.daangar.gangame.sdk.Deal): Deal {
        return Deal(deal.title,
                deal.salePrice,
                deal.normalPrice,
                deal.metacriticScore,
                deal.steamRating,
                deal.thumb)
    }

}