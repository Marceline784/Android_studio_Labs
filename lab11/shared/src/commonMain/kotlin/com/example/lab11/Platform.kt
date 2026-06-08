package com.example.lab11

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform