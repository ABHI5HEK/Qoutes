package com.example.qoutes

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET



interface QouteInterFace{

    @GET("random")
    fun getQoutes() : Call<QouteContent>

}
