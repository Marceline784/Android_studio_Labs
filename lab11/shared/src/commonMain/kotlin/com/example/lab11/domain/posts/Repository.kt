package com.example.lab11.domain.posts

import com.example.lab11.data.common.NetworkResult
import com.example.lab11.data.posts.model.requests.NewPost
import com.example.lab11.data.posts.model.responses.DeletedPost
import com.example.lab11.data.posts.model.responses.Post
import com.example.lab11.data.posts.model.responses.Posts

interface Repository {

    suspend fun getPosts(): NetworkResult<Posts>

    suspend fun addPost(
        post: NewPost
    ): NetworkResult<Post>

    suspend fun updatePost(
        post: Post
    ): NetworkResult<Post>

    suspend fun deletePost(
        id: Int
    ): NetworkResult<DeletedPost>
}