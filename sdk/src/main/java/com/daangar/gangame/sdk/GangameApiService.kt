package com.daangar.gangame.sdk

import com.daangar.gangame.sdk.serializer.ListTopGameDeserializer
import com.daangar.gangame.sdk.serializer.TopGameDeserializer
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class GangameApiService(val apiConfig: IGangameApiConfig = GangameClientConfig()) {

    val apiClient: IRetrofitGangameApi

    init {

        val tokenType = object: TypeToken<ArrayList<TopGame>>(){}.type

        val gson = GsonBuilder()
                .registerTypeAdapter(TopGame::class.java, TopGameDeserializer())
                .registerTypeAdapter(tokenType, ListTopGameDeserializer())
                .create()


        val apiClientConfig = Retrofit.Builder()
                .baseUrl(Routes.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())

        apiConfig.setupConfig(apiClientConfig)

        apiClient = apiClientConfig.build().create(IRetrofitGangameApi::class.java)
    }
}