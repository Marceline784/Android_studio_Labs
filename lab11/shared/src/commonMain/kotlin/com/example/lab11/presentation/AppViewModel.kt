package com.example.lab11.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lab11.data.common.NetworkResult
import com.example.lab11.data.posts.model.requests.NewPost
import com.example.lab11.data.posts.model.responses.Post
import com.example.lab11.data.posts.model.responses.Reactions
import com.example.lab11.domain.posts.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AppViewModel(
    private val repository: Repository
) : ViewModel() {

    private val _state = MutableStateFlow(AppState())
    val state: StateFlow<AppState> = _state.asStateFlow()

    fun getPosts() {

        viewModelScope.launch {

            _state.value = AppState(isLoading = true)

            when (val result = repository.getPosts()) {

                is NetworkResult.Success -> {

                    val text = buildString {

                        appendLine("===== POSTS =====")
                        appendLine()

                        result.data.posts.take(10).forEach { post ->

                            appendLine("ID: ${post.id}")
                            appendLine("Title: ${post.title}")
                            appendLine("Body: ${post.body}")
                            appendLine("----------------------------")
                        }
                    }

                    _state.value = AppState(
                        successMessage = text
                    )
                }

                is NetworkResult.Failure -> {
                    _state.value = AppState(
                        errorMessage = result.errorMessage
                    )
                }
            }
        }
    }

    fun addPost() {

        viewModelScope.launch {

            _state.value = AppState(isLoading = true)

            when (
                val result = repository.addPost(
                    NewPost(
                        title = "New Post",
                        body = "Created from app",
                        userId = 1,
                        reactions = Reactions()
                    )
                )
            ) {

                is NetworkResult.Success -> {

                    _state.value = AppState(
                        successMessage =
                            """
POST SUCCESS

Created post:

ID: ${result.data.id}
Title: ${result.data.title}
Body: ${result.data.body}
                        """.trimIndent()
                    )
                }

                is NetworkResult.Failure -> {
                    _state.value = AppState(
                        errorMessage = result.errorMessage
                    )
                }
            }
        }
    }

    fun updatePost() {

        viewModelScope.launch {

            _state.value = AppState(isLoading = true)

            when (
                val result = repository.updatePost(
                    Post(
                        id = 1,
                        title = "Updated Post",
                        body = "Updated body",
                        userId = 1,
                        tags = emptyList(),
                        reactions = Reactions(),
                        views = 0
                    )
                )
            ) {

                is NetworkResult.Success -> {

                    _state.value = AppState(
                        successMessage =
                            """
PUT SUCCESS

Updated post:

ID: ${result.data.id}
Title: ${result.data.title}
Body: ${result.data.body}
                        """.trimIndent()
                    )
                }

                is NetworkResult.Failure -> {
                    _state.value = AppState(
                        errorMessage = result.errorMessage
                    )
                }
            }
        }
    }

    fun deletePost() {

        viewModelScope.launch {

            _state.value = AppState(isLoading = true)

            when (val result = repository.deletePost(1)) {

                is NetworkResult.Success -> {

                    _state.value = AppState(
                        successMessage =
                            """
DELETE SUCCESS

Deleted post:

ID: ${result.data.id}
Title: ${result.data.title}

isDeleted = ${result.data.isDeleted}
                        """.trimIndent()
                    )
                }

                is NetworkResult.Failure -> {
                    _state.value = AppState(
                        errorMessage = result.errorMessage
                    )
                }
            }
        }
    }
}