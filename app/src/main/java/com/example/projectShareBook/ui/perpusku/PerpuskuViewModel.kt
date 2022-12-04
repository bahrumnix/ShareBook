package com.example.projectShareBook.ui.perpusku

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PerpuskuViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is My Library"
    }
    val text: LiveData<String> = _text
}
