package com.example.lab11.data.posts

import com.example.lab11.data.common.NetworkResult
import com.example.lab11.data.posts.model.requests.NewPost
import com.example.lab11.data.posts.model.responses.DeletedPost
import com.example.lab11.data.posts.model.responses.Post
import com.example.lab11.data.posts.model.responses.Posts

const val BASE_URL = "https://dummyjson.com/"
const val POSTS_API = "posts"
const val ADD_POST = "add"

interface ApiService {

    suspend fun getAllPosts(): NetworkResult<Posts>

    suspend fun addPost(
        post: NewPost
    ): NetworkResult<Post>

    suspend fun updatePost(
        post: Post
    ): NetworkResult<Post>

    suspend fun deletePost(
        postId: Int
    ): NetworkResult<DeletedPost>
}