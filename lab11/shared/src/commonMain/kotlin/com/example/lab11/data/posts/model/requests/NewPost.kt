package com.example.lab11.data.posts.model.requests

import kotlinx.serialization.Serializable
import com.example.lab11.data.posts.model.responses.Reactions

@Serializable
data class NewPost(
    val body: String = "",
    val reactions: Reactions,
    val tags: List<String> = emptyList(),
    val title: String = "",
    val userId: Int,
    val views: Int = 0
)