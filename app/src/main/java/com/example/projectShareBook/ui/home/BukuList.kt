package com.example.projectShareBook.ui.home

import com.example.projectShareBook.R

class BukuList {
    private val daftar = mutableListOf<Buku>()

    init {
        daftar.add(Buku(R.drawable.cover1, "Bulan","Tere Liye"))
        daftar.add(Buku(R.drawable.cover2, "Matahari","Tere Liye"))
        daftar.add(Buku(R.drawable.cover3, "Meteor","Tere Liye"))
        daftar.add(Buku(R.drawable.cover4, "Bumi","Tere Liye"))
    }

    fun getBuku() = daftar

    fun addBuku(data : Buku){
        daftar.add(data)
    }
}