package com.etang.simpletwitterclone.ui.post

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.etang.simpletwitterclone.R
import com.etang.simpletwitterclone.data.model.Post

class CommentAdapter(private val comments: List<Post>) : RecyclerView.Adapter<CommentAdapter.CommentViewHolder>() {

    inner class CommentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewCommentAuthor: TextView = itemView.findViewById(R.id.textViewAuthor) // Utilise textViewAuthor de item_post.xml
        val textViewCommentContent: TextView = itemView.findViewById(R.id.textViewContent) // Utilise textViewContent de item_post.xml
        val textViewCommentLikes: TextView = itemView.findViewById(R.id.textViewLikes)
        val imageButtonLike: ImageButton = itemView.findViewById(R.id.imageButtonLike)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        // Utilise le layout item_post.xml pour afficher un commentaire comme un post
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
        return CommentViewHolder(view)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        val comment = comments[position]

        // Affiche les détails du commentaire
        holder.textViewCommentAuthor.text = comment.author.username
        holder.textViewCommentContent.text = comment.content
        holder.textViewCommentLikes.text = "${comment.likes} likes"

        // Gère le clic sur le bouton Like pour ajouter un like au commentaire
        holder.imageButtonLike.setOnClickListener {
            comment.likes += 1
            holder.textViewCommentLikes.text = "${comment.likes} likes"
        }
    }

    override fun getItemCount(): Int = comments.size
}
