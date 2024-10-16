package com.etang.simpletwitterclone.data.model

data class User(
    val id: Int,
    val username: String,
    val displayName: String,
    val password: String,
    val bio: String,
    val birthday: String
)
