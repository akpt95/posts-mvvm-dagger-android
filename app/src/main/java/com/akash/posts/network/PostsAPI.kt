package com.akash.posts.network

import com.akash.posts.network.model.PostsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PostsAPI {

    @GET("posts")
    fun getPosts(): Call<PostsResponse>

}