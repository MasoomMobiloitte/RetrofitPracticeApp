package com.example.retrofitpracticeapp

import retrofit2.Response
import retrofit2.http.GET

interface TodoApi {

    @GET("todos")
    suspend fun getTitle() : Response<List<ModelClass>>
}