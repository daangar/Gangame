package com.daangar.gangame.data

import com.daangar.gangame.TopGame

object TopGameMapper {

    fun fromSdk(topGame: com.daangar.gangame.sdk.TopGame, position: Int) : TopGame {
        return TopGame(topGame.title,
                topGame.owners,
                topGame.steamRating,
                topGame.publisher,
                topGame.price,
                position,
                topGame.thumb)
    }

}