package com.akash.posts.ui

import com.akash.posts.network.model.Data
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class PostsRecyclerViewAdapterTest {

    private lateinit var postsRecyclerViewAdapter: PostsRecyclerViewAdapter

    @Mock
    private lateinit var postsDataList: List<Data>

    @Before
    fun setup(){
        postsRecyclerViewAdapter = PostsRecyclerViewAdapter()
    }

    @Test
    fun testGetItem_ListNotSet(){
        assertEquals(0, postsRecyclerViewAdapter.itemCount)
    }

    @Test
    fun testGetItem_EmptyList() {
        `when`(postsDataList.size).thenReturn(2)
        postsRecyclerViewAdapter.setUpdatedData(postsDataList)

        assertEquals(2 ,postsRecyclerViewAdapter.itemCount)
    }
}