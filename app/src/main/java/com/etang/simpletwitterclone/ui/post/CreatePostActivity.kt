package com.etang.simpletwitterclone.ui.post

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.etang.simpletwitterclone.R
import com.etang.simpletwitterclone.data.model.Post
import com.etang.simpletwitterclone.data.repository.FakeRepository

class CreatePostActivity : AppCompatActivity() {

    private var parentPostId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_post)

        // Récupérer l'ID du post parent si c'est un commentaire
        parentPostId = intent.getIntExtra("PARENT_POST_ID", -1).takeIf { it != -1 }

        val editTextContent: EditText = findViewById(R.id.editTextContent)
        val buttonPost: Button = findViewById(R.id.buttonPost)

        // Gérer le clic sur le bouton "Post"
        buttonPost.setOnClickListener {
            val content = editTextContent.text.toString()
            val newPost = Post(
                id = generateNewPostId(), // Génère un nouvel ID unique
                content = content,
                timestamp = System.currentTimeMillis(),
                author = FakeRepository.getUsers().first() // Utilise un utilisateur fictif pour l'exemple
            )

            // Ajouter le post en tant que post principal
            FakeRepository.addPost(newPost)

            // Si c'est un commentaire, l'ajouter au post parent
            parentPostId?.let { parentId ->
                val parentPost = FakeRepository.getPosts().find { it.id == parentId }
                parentPost?.comments?.add(newPost) // Ajouter le commentaire à la liste des commentaires
            }

            // Fermer l'activité de création de post
            finish()
        }
    }

    private fun generateNewPostId(): Int {
        // Logique pour générer un nouvel ID unique
        return FakeRepository.getPosts().maxOfOrNull { it.id }?.plus(1) ?: 1
    }
}