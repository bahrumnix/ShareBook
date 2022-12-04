package com.example.projectShareBook.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel(val list: BukuList)  : ViewModel()  {
    private val buku = MutableLiveData<List<Buku>>()

    fun getBukuku() :LiveData<List<Buku>>{
        buku.value = list.getBuku()
        return buku
    }

    fun addBukuku(data : Buku){
        list.addBuku(data)
        buku.value = list.getBuku()
    }
}