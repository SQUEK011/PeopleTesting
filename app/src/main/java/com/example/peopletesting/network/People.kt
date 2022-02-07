package com.example.peopletesting.network

import com.google.gson.annotations.SerializedName

data class People (
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("avatar")
    val avatar: String,
    @SerializedName("jobTitle")
    val jobTitle: String,
    @SerializedName("id")
    val id: Int
)