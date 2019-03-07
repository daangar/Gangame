package com.daangar.gangame.sdk.serializer

import com.daangar.gangame.sdk.TopGame
import com.google.gson.Gson
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class TopGameDeserializer : JsonDeserializer<TopGame> {

     companion object {
        const val BASE_IMG_URL = "http://cdn.akamai.steamstatic.com/steam/apps/%s/capsule_184x69.jpg"
     }

    override fun deserialize(json: JsonElement, typeOfT: Type?, context: JsonDeserializationContext?): TopGame {
        val gson = Gson()
        val topGame = gson.fromJson(json, TopGame::class.java)
        val appId = json.asJsonObject["appid"].asInt.toString()
        val thumb = String.format(BASE_IMG_URL, appId)
        topGame.thumb = thumb
        topGame.price = topGame.price / 100

        return topGame
    }
}