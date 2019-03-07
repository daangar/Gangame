package com.daangar.gangame.sdk.serializer

import com.daangar.gangame.sdk.TopGame
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class ListTopGameDeserializer : JsonDeserializer<ArrayList<TopGame>> {
    override fun deserialize(json: JsonElement, typeOfT: Type?, context: JsonDeserializationContext?): ArrayList<TopGame> {
        val jsonTopGames = json.asJsonObject.entrySet()
                .map { (_, json) ->
                    json.asJsonObject
                }

        val gson = GsonBuilder()
                .registerTypeAdapter(TopGame::class.java, TopGameDeserializer())
                .create()

        val listTopGames: List<TopGame> = jsonTopGames.map { iJson ->
            gson.fromJson(iJson, TopGame::class.java)
        }

        val arrayListTopGames: ArrayList<TopGame> = arrayListOf()
        arrayListTopGames.addAll(listTopGames)

        return arrayListTopGames

    }
}