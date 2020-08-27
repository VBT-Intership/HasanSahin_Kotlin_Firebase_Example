package com.example.generalproject.UI.RecyleViews.holder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.example.generalproject.R
import com.example.generalproject.Models.Post

class PostListViewHolder(var container: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(container.context).inflate(
        R.layout.postlist,
        container,
        false
    )
) {

    val singlePost = itemView.findViewById<LinearLayout>(R.id.singlePost)

    val title = itemView.findViewById<TextView>(R.id.title)
    val body = itemView.findViewById<TextView>(R.id.body)

    fun bind(postModel: Post, positions: Int) {
        title.text = postModel.title
        body.text = postModel.body
        singlePost.setOnLongClickListener{
            showPopup(singlePost,positions)
            true
        }

    }
    private fun showPopup(view: View, positions: Int) {
        val popup = PopupMenu(container.context, view)
        popup.inflate(R.menu.postlist_popup)

        popup.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item->

            when(item.itemId) {
                R.id.action_detail ->
                    Toast.makeText(container.context, "Seçili Kayıt Silindi", Toast.LENGTH_SHORT).show()
                R.id.action_delete ->{

                    Toast.makeText(container.context, "Seçili Kayıt Silindi" , Toast.LENGTH_SHORT).show()
                }
            }
            true
        })
        popup.show()
    }

}