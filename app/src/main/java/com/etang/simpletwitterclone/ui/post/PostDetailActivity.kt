package com.etang.simpletwitterclone.ui.post

import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.etang.simpletwitterclone.R
import com.etang.simpletwitterclone.data.model.Post
import com.etang.simpletwitterclone.data.repository.FakeRepository

class PostDetailActivity : AppCompatActivity() {

    private lateinit var post: Post

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_detail)

        // Récupérer l'ID du post depuis l'Intent
        val postId = intent.getIntExtra("POST_ID", -1)

        // Récupérer le post par son ID
        post = FakeRepository.getPosts().find { it.id == postId } ?: return

        // Initialiser les vues
        val textViewAuthor: TextView = findViewById(R.id.textViewDetailAuthor)
        val textViewDate: TextView = findViewById(R.id.textViewDetailDate)
        val textViewContent: TextView = findViewById(R.id.textViewDetailContent)
        val textViewLikes: TextView = findViewById(R.id.textViewLikes)
        val textViewComments: TextView = findViewById(R.id.textViewComment)
        val imageButtonLike: ImageButton = findViewById(R.id.imageButtonLike)
        val recyclerViewComments: RecyclerView = findViewById(R.id.recyclerViewComments)

        // Afficher les détails du post
        textViewAuthor.text = post.author.username
        textViewDate.text = java.text.SimpleDateFormat("dd/MM/yyyy", java.util.Locale.getDefault()).format(post.timestamp)
        textViewContent.text = post.content
        textViewLikes.text = "${post.likes} likes"
        textViewComments.text = "${post.comments.size} comments"

        // Gérer le clic sur le bouton Like
        imageButtonLike.setOnClickListener {
            post.likes += 1
            textViewLikes.text = "${post.likes} likes"
        }

        // Configurer la RecyclerView pour afficher les commentaires
        recyclerViewComments.layoutManager = LinearLayoutManager(this)
        recyclerViewComments.adapter = PostAdapter(post.comments) // Réutiliser PostAdapter pour les commentaires
    }

    // Dans PostDetailActivity, on met à jour l'adapter après la création d'un commentaire
    override fun onResume() {
        super.onResume()
        // Rafraîchir les commentaires du post
        val recyclerViewComments: RecyclerView = findViewById(R.id.recyclerViewComments)
        recyclerViewComments.adapter = CommentAdapter(post.comments)
    }

}