package com.example.projectShareBook.ui.perpusku

import android.app.Activity.RESULT_OK
import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.projectShareBook.R
import com.example.projectShareBook.databinding.FragmentFormTambahBinding
import androidx.navigation.findNavController
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_form_tambah.view.*

class FormTambahFragment : Fragment() {
    // Access a Cloud Firestore instance from your Activity

    val db = Firebase.firestore
    lateinit var ImageUri : Uri



    private var _binding: FragmentFormTambahBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = FragmentFormTambahBinding.inflate(layoutInflater, container, false)
        binding.btnInputBuku.setOnClickListener {
            val judul = binding.textJudulBuku.text.toString()
            val penulis = binding.textPenulisBuku.text.toString()
            val penerbit = binding.textPenerbitBuku.text.toString()
            val tahun = binding.textTahunTerbit.text.hashCode()
            val sinopsis = binding.textSinopsisBuku.text.toString()
            val jumlah = binding.textJumlahBuku.text.hashCode()
            val jenisPeminjaman = binding.jenisPeminjaman.checkedRadioButtonId.toString()
            val harga = binding.textHargaBuku.text.hashCode()

            binding.inputImage.setOnClickListener {
                selectImage()
            }
            it.findNavController().navigate(R.id.fragment_Perpusku)
            tambahBuku(judul,penulis,penerbit,tahun,sinopsis,jumlah,jenisPeminjaman,harga,null)
        }
        return binding.root
    }

    private fun selectImage() {
        val intent = Intent()
        intent.type = "images/*"
        intent.action = Intent.ACTION_GET_CONTENT

        startActivityForResult(intent,100)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 100 && requestCode == RESULT_OK){
            ImageUri = data?.data!!

        }

    }
    private fun uploadImage(){
        val progressDialog = ProgressDialog(requireContext())


    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
    private fun tambahBuku(judul : String, penulis : String,penerbit: String,
                             tahun : Int, sinopsis :String, jumlah : Int,
                             jenisPeminjaman : String, harga : Int, image : String?){
        val user = hashMapOf(
            "judul" to judul,
            "penulis" to penulis,
            "penerbit" to penerbit,
            "tahun" to tahun,
            "sinopsis" to sinopsis,
            "jumlah" to jumlah,
            "jenisPeminjaman" to jenisPeminjaman,
            "harga" to harga,
            "image" to image
        )
        db.collection("profile")
            .add(user)
            .addOnSuccessListener { documentReference ->
                Log.d("profile", "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w("profile", "Error adding document", e)
            }
    }
}