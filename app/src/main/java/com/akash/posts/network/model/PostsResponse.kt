package com.akash.posts.network.model

import com.google.gson.annotations.SerializedName

data class PostsResponse(
    @SerializedName("meta")
    var meta: Meta? = null,
    @SerializedName("data")
    var data: List<Data>? = null
)
