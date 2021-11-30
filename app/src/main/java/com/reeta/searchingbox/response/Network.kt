package com.reeta.searchingbox.response

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Network {

    val BASE_URL="https://digi-api.airtel.in/"
    var queryString="airtel"
    var city="gurgaon"

    fun getRetrofit()= Retrofit.Builder().baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create()).build()

    fun getApiService()= getRetrofit().create(ApiCalling::class.java)
}