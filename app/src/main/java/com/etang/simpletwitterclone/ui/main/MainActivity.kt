package com.etang.simpletwitterclone.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.etang.simpletwitterclone.R
import com.etang.simpletwitterclone.data.repository.FakeRepository
import com.etang.simpletwitterclone.ui.post.CreatePostActivity
import com.etang.simpletwitterclone.ui.post.PostAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Récupérer la RecyclerView
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewPosts)

        // Récupérer les posts fictifs
        val posts = FakeRepository.getPosts()

        // Créer et attacher l'Adapter à la RecyclerView
        val adapter = PostAdapter(posts)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Récupérer le FloatingActionButton
        val fabCreatePost = findViewById<FloatingActionButton>(R.id.fabCreatePost)

        // Gérer le clic sur le FAB pour ouvrir CreatePostActivity
        fabCreatePost.setOnClickListener {
            val intent = Intent(this, CreatePostActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        // Rafraîchir la liste des posts lorsque l'activité est reprise
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewPosts)
        val posts = FakeRepository.getPosts()
        recyclerView.adapter = PostAdapter(posts)
    }
}