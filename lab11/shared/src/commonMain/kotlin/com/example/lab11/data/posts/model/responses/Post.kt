package com.example.lab11.data.posts.model.responses

import kotlinx.serialization.Serializable

@Serializable
data class Post(
    val body: String = "",
    val id: Int = 0,
    val reactions: Reactions = Reactions(),
    val tags: List<String> = emptyList(),
    val title: String = "",
    val userId: Int = 0,
    val views: Int = 0
)