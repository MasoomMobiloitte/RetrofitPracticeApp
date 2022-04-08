package com.example.retrofitpracticeapp

import com.google.gson.annotations.SerializedName

data class ModelClass(
    val completed: Boolean,
    val id: Int,
    val title: String,
    val userId: Int
)