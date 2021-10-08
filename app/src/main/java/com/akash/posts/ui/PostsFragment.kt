package com.akash.posts.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.akash.posts.databinding.PostsFragmentBinding
import com.akash.posts.network.model.Data
import com.google.android.material.progressindicator.CircularProgressIndicator
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class PostsFragment : DaggerFragment() {

    private var _binding: PostsFragmentBinding? = null
    private val binding get() = _binding!!
    private val postsRecyclerViewAdapter: PostsRecyclerViewAdapter by lazy {
        PostsRecyclerViewAdapter()
    }

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProvider.Factory

    private val postsViewModel: PostsViewModel by viewModels {
        viewModelProviderFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = PostsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initViewModel()
        loadPosts()
    }

    private fun initRecyclerView() {
        binding.postsRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.postsRecyclerView.adapter = postsRecyclerViewAdapter
    }

    /**
     * This method initializes the view model, observes on the Live Data and updates the Adapter
     */
    private fun initViewModel() {
        postsViewModel.getPostsLiveDataObserver().observe(viewLifecycleOwner, object:Observer<List<Data>>{
            override fun onChanged(dataList: List<Data>?) {
                if (dataList!= null){
                    binding.progressCircular.visibility = View.GONE
                    binding.postsRecyclerView.visibility = View.VISIBLE
                    binding.noResultText.visibility = View.GONE
                    postsRecyclerViewAdapter.setUpdatedData(dataList)
                    postsRecyclerViewAdapter.notifyDataSetChanged()
                } else {
                    binding.progressCircular.visibility = View.GONE
                    binding.postsRecyclerView.visibility = View.GONE
                    binding.noResultText.visibility = View.VISIBLE
                }
            }
        })
    }

    private fun loadPosts() {
        Log.i(TAG, "post loading: ")
        binding.progressCircular.visibility = View.VISIBLE
        binding.postsRecyclerView.visibility = View.GONE
        binding.noResultText.visibility = View.GONE
        postsViewModel.loadPosts()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}