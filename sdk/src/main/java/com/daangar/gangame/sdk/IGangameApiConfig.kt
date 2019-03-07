package com.daangar.gangame.sdk

import retrofit2.Retrofit

interface IGangameApiConfig {
    fun setupConfig(builder: Retrofit.Builder)
}