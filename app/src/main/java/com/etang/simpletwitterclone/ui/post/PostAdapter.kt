package com.etang.simpletwitterclone.ui.post

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.etang.simpletwitterclone.R
import com.etang.simpletwitterclone.data.model.Post
import com.etang.simpletwitterclone.data.repository.FakeRepository

class PostAdapter(private val posts: List<Post>) : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    inner class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewAuthor: TextView = itemView.findViewById(R.id.textViewAuthor)
        val textViewContent: TextView = itemView.findViewById(R.id.textViewContent)
        val textViewLikes: TextView = itemView.findViewById(R.id.textViewLikes)
        val imageButtonLike: ImageButton = itemView.findViewById(R.id.imageButtonLike)
        val imageButtonComment: ImageButton = itemView.findViewById(R.id.imageButtonComment)
        val textViewComments: TextView = itemView.findViewById(R.id.textViewComment)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = posts[position]
        holder.textViewAuthor.text = post.author.username
        holder.textViewContent.text = post.content
        holder.textViewLikes.text = "${post.likes} likes"
        holder.textViewComments.text = "${post.comments.size} comments"

        // Gérer le clic sur le post pour afficher les détails
        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, PostDetailActivity::class.java)
            intent.putExtra("POST_ID", post.id) // Passe l'ID du post aux détails
            context.startActivity(intent)
        }

        // Gérer le clic sur le bouton Like
        holder.imageButtonLike.setOnClickListener {
            post.likes += 1
            holder.textViewLikes.text = "${post.likes} likes"
        }

        // Gérer le clic sur le bouton Comment
        holder.imageButtonComment.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, CreatePostActivity::class.java)
            intent.putExtra("PARENT_POST_ID", post.id) // Passe l'ID du post parent
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return posts.size
    }
}