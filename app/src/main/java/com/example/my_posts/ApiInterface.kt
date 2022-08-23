package com.example.my_posts

import org.w3c.dom.Comment
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {
    @GET("/posts")
    fun getPosts(): Call<List<Post>>
    @GET("/posts/{postId}")
    fun getPostsById(@Path("postId")postId: Int):Call<Post>
    @GET("/posts/{postId}/comment")
    fun getComment(@Path("postId")comment:Int):Call<List<Comment>>
}