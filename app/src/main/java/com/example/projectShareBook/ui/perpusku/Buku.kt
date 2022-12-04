package com.example.projectShareBook.ui.perpusku

data class Buku(val idUser : String,var judul:String, var penerbit:String,var penulis:String,
                var tahun: Int, var sinopsis : String,var image : String,
                var jumlahBuku : Int, var jenisPeminjaman: String, var hargaBuku:Int? )
