package com.akash.posts.network.model

import com.google.gson.annotations.SerializedName

data class Links(
    @SerializedName("previous")
    val previous: String?,
    @SerializedName("current")
    val current: String?,
    @SerializedName("next")
    val next: String?
)
