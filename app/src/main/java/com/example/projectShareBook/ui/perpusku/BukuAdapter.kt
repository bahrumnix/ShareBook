package com.example.projectShareBook.ui.perpusku

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.projectShareBook.databinding.ItemBukuBinding

class BukuAdapter: RecyclerView.Adapter<BukuAdapter.OpenViewHolder>() {

    private var itemList = ArrayList<Buku>()

    var onItemClick: ((Buku) -> Unit)? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setItems(items: List<Buku>?) {
        itemList.clear()
        if (items != null) {
            itemList.addAll(items)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OpenViewHolder {
        val view =
            ItemBukuBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OpenViewHolder(view)
    }

    override fun onBindViewHolder(holder: OpenViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    inner class OpenViewHolder(
        private val binding: ItemBukuBinding
    ): RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(itemList[adapterPosition])
            }
        }


        fun bind(buku: Buku) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(buku.image)
                    .into(gambarBuku)
                judulBuku.text = buku.judul
                namaPenulis.text = buku.penulis
            }
        }
    }

    override fun getItemCount(): Int = itemList.size


}
