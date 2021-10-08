package com.akash.posts.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.akash.posts.network.PostsAPI
import com.akash.posts.network.model.Data
import com.akash.posts.network.model.PostsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

const val TAG = "PostsViewModel"

/**
 * This is the ViewModel that takes care of hitting the posts API and retrieving the Data
 * Injected constructor receives the dependency for PostsAPI
 */
class PostsViewModel @Inject constructor(private val postsAPI: PostsAPI) : ViewModel() {

    private var postsLiveData: MutableLiveData<List<Data>> = MutableLiveData()

    fun getPostsLiveDataObserver(): MutableLiveData<List<Data>> {
        return postsLiveData
    }

    fun loadPosts() {
        val postsCall: Call<PostsResponse> = postsAPI.getPosts()

        postsCall.enqueue(object: Callback<PostsResponse>{
            override fun onResponse(call: Call<PostsResponse>, response: Response<PostsResponse>) {
                if (response.isSuccessful)
                    postsLiveData.postValue(response.body()?.data)
                else
                    postsLiveData.postValue(null)
            }

            override fun onFailure(call: Call<PostsResponse>, t: Throwable) {
                postsLiveData.postValue(null)
                Log.e(TAG, "Posts response failed: ",t)
            }

        })
    }
}