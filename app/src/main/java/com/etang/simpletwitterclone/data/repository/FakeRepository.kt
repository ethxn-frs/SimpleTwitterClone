package com.etang.simpletwitterclone.data.repository

import com.etang.simpletwitterclone.data.model.Post
import com.etang.simpletwitterclone.data.model.User

object FakeRepository {

    // Liste fictive des utilisateurs
    private val users = listOf(
        User(id = 1, username = "john_doe", displayName = "John Doe", password = "password123", bio = "Tech Enthusiast", birthday = "01/01/2000"),
        User(id = 2, username = "jane_doe", displayName = "Jane Doe", password = "password456", bio = "Love to travel", birthday = "02/02/1998")
    )

    // Liste fictive des posts
    private val posts = mutableListOf(
        Post(id = 1, content = "Hello, this is my first post!", timestamp = System.currentTimeMillis(), likes = 10, author = users[0]),
        Post(id = 2, content = "Enjoying a great vacation!", timestamp = System.currentTimeMillis(), likes = 5, author = users[1])
    )

    // Fonction pour récupérer tous les utilisateurs
    fun getUsers(): List<User> = users

    // Fonction pour récupérer tous les posts
    fun getPosts(): List<Post> = posts

    // Fonction pour ajouter un nouveau post
    fun addPost(post: Post) {
        posts.add(post)
    }

    // Fonction pour ajouter un commentaire à un post
    fun addComment(postId: Int, comment: Post) {
        // Trouve le post par son ID
        posts.find { it.id == postId }?.comments?.add(comment)
    }
}
