package com.example.lab11.data.posts.model.responses

import kotlinx.serialization.Serializable

@Serializable
data class Reactions(
    val dislikes: Int = 0,
    val likes: Int = 0
)