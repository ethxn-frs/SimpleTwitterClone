package com.etang.simpletwitterclone.data.model

data class Post(
    val id: Int,
    val content: String,
    val timestamp: Long,
    var likes: Int = 0,
    val author: User,
    var comments: MutableList<Post> = mutableListOf()
)
