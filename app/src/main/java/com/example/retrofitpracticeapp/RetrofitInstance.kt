package com.example.retrofitpracticeapp

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    val api : TodoApi by lazy{
        Retrofit.Builder()
            .baseUrl(ServiceConstant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TodoApi::class.java)
    }
}