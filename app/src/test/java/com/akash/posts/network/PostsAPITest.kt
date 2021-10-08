package com.akash.posts.network

import com.akash.posts.network.model.Data
import com.akash.posts.network.model.PostsResponse
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@RunWith(JUnit4::class)
class PostsAPITest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var postsAPI: PostsAPI

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        postsAPI = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(PostsAPI::class.java)
    }

    @After
    fun tearDown(){
        mockWebServer.shutdown()
    }

    @Test
    fun testGetPosts() {
        enqueueResponse("mock-posts-response.json")
        val postsResponse: PostsResponse? = postsAPI.getPosts().execute().body()
        val postsList: List<Data>? = postsResponse?.data

        assertNotNull(postsResponse)
        assertNotNull(postsList)
        assertEquals("testTitle", postsList?.get(0)?.title)
        assertEquals("testBody", postsList?.get(0)?.body)
    }

    @Test
    fun testGetPosts_EmptyData() {
        enqueueResponse("mock-posts-response-empty.json")
        val postsResponse: PostsResponse? = postsAPI.getPosts().execute().body()
        val postsList: List<Data>? = postsResponse?.data

        assertNotNull(postsResponse)
        assertEquals(emptyList<Data>(), postsList)
    }

    private fun enqueueResponse(fileName: String, headers: Map<String, String> = emptyMap()) {
        val inputStream = javaClass.classLoader!!
            .getResourceAsStream("mock-response/$fileName")
        val source = inputStream.source().buffer()
        val mockResponse = MockResponse()
        for ((key, value) in headers) {
            mockResponse.addHeader(key, value)
        }
        mockWebServer.enqueue(
            mockResponse
                .setBody(source.readString(Charsets.UTF_8))
        )
    }
}