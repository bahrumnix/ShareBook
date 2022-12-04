package com.example.projectShareBook.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.Nullable
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.projectShareBook.R

class BukuAdapter(var buku : LiveData<List<Buku>>) : RecyclerView.Adapter<BukuAdapter.ContentViewHolder>(){

        inner class ContentViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
            var image : ImageView = itemView.findViewById(R.id.gambarBuku)
            var judul : TextView = itemView.findViewById(R.id.judulBuku)
            var penulis : TextView = itemView.findViewById(R.id.namaPenulis)
        }

        override fun onBindViewHolder(holder: ContentViewHolder, position: Int) {
            holder.itemView.apply {
                buku.value?.get(position)?.let { holder.image.setImageResource(it.gambarBuku) }
                holder.judul.text = buku.value?.get(position)?.judulBuku?: ""
                holder.penulis.text = buku.value?.get(position)?.namaPenulis?: ""
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_buku, parent, false)
            return ContentViewHolder(view)
        }

        override fun getItemCount(): Int {
            return buku.value?.size?: 0
        }


}