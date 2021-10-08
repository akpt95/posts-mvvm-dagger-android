package com.akash.posts.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.akash.posts.databinding.PostsRecyclerViewItemBinding
import com.akash.posts.network.model.Data

class PostsRecyclerViewAdapter: RecyclerView.Adapter<PostsRecyclerViewAdapter.PostsViewHolder>() {

    private var postsDataList: List<Data>? = arrayListOf()

    /**
     * This receives the list of Posts Data after API call from the Fragment
     */
    fun setUpdatedData(postsDataList: List<Data>) {
        this.postsDataList = postsDataList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PostsRecyclerViewItemBinding.inflate(inflater, parent, false)
        return PostsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
        holder.bind(postsDataList?.get(position))
    }

    override fun getItemCount(): Int {
        return postsDataList?.size ?: 0
    }

    class PostsViewHolder(private val binding: PostsRecyclerViewItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(postsData: Data?){
            binding.postsData = postsData
        }
    }
}