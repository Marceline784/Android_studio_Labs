package com.example.lab11.domain.posts

import com.example.lab11.data.common.NetworkResult
import com.example.lab11.data.posts.ApiService
import com.example.lab11.data.posts.model.requests.NewPost
import com.example.lab11.data.posts.model.responses.DeletedPost
import com.example.lab11.data.posts.model.responses.Post
import com.example.lab11.data.posts.model.responses.Posts

class RepositoryImpl(
    private val apiService: ApiService
) : Repository {

    override suspend fun getPosts(): NetworkResult<Posts> {
        return apiService.getAllPosts()
    }

    override suspend fun addPost(
        post: NewPost
    ): NetworkResult<Post> {
        return apiService.addPost(post)
    }

    override suspend fun updatePost(
        post: Post
    ): NetworkResult<Post> {
        return apiService.updatePost(post)
    }

    override suspend fun deletePost(
        id: Int
    ): NetworkResult<DeletedPost> {
        return apiService.deletePost(id)
    }
}