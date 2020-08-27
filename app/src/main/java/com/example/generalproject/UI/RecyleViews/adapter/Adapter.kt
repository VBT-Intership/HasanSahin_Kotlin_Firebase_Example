package com.example.generalproject.UI.RecyleViews.adapter

import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.example.generalproject.UI.Activity.HomePage
import com.example.generalproject.Models.Post
import com.example.generalproject.R

class MainAdapter(var items: MutableList<Post>, val context: HomePage) : RecyclerView.Adapter<MainAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            = MainHolder(LayoutInflater.from(parent.context).inflate(R.layout.postlist, parent, false))

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        holder.bind(items[position],position)
    }
    fun removePost(position:Int){
        context.posts.removeAt(position)
        notifyDataSetChanged()
    }
    inner class MainHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val singlePost = itemView.findViewById<LinearLayout>(R.id.singlePost)
        private val title = itemView.findViewById<TextView>(R.id.title)
        private val body = itemView.findViewById<TextView>(R.id.body)

        fun bind(item: Post, position: Int) { // bind işlemi
            title.text = item.title
            body.text = item.body
            singlePost.setOnClickListener(){
                showPopup(singlePost,position)
                true
            }
        }
    }
    private fun showPopup(view: View, positions: Int) { // Üzerine basınca  gelen popup
        val popup = PopupMenu(context, view)
        popup.inflate(R.menu.postlist_popup)
        popup.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item->
            when(item.itemId) {
                R.id.action_detail ->{
                    Toast.makeText(context, "Seçili Kayıt Getirildi", Toast.LENGTH_SHORT).show()
                    showDialog(context,context.posts.get(positions).title,context.posts.get(positions).body)
                }
                R.id.action_delete ->{
                    removePost(positions)
                    Toast.makeText(context, "Seçili Kayıt Silindi:"+positions , Toast.LENGTH_SHORT).show()
                }
            }
            true
        })
        popup.show()
    }
    fun showDialog(context: HomePage, title: String?, body: String?){ // Body Detail Popup
        val mAlertDialog = AlertDialog.Builder(this.context)
        mAlertDialog.setIcon(R.drawable.ic_speaker_notes_black_24dp)
        mAlertDialog.setTitle(title)
        mAlertDialog.setMessage(body)
        mAlertDialog.setNegativeButton("cancel",{ dialogInterface: DialogInterface, i: Int -> dialogInterface.dismiss()})
        mAlertDialog.show()
    }

    interface Callback {
        fun onItemClicked(item: Post)
    }

}