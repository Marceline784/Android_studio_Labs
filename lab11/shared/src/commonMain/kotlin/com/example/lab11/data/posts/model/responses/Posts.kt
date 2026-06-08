package com.example.lab11.data.posts.model.responses

import kotlinx.serialization.Serializable

@Serializable
data class Posts(
    val limit: Int,
    val posts: List<Post>,
    val skip: Int,
    val total: Int
)