package com.akash.posts.network.model

import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("id")
    val id : Int,
    @SerializedName("user_id")
    val userId : Int,
    @SerializedName("title")
    val title : String?,
    @SerializedName("body")
    val body : String?
)
