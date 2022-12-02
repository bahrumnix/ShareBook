package com.example.sharebook.ui.perpusku

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.sharebook.R
import com.example.sharebook.databinding.FragmentPerpuskuBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class PerpuskuFragment : Fragment() {
    private lateinit var perpuskuViewModel: PerpuskuViewModel
    private var _binding : FragmentPerpuskuBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        perpuskuViewModel = ViewModelProvider(this).get(PerpuskuViewModel::class.java)
       _binding = FragmentPerpuskuBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnTambahbuku.setOnClickListener {
            it.findNavController().navigate(R.id.action_navigation_perpusku_to_formTambahFragment)
        }
        val bukuAdapter = BukuAdapter()
        Firebase.firestore.collection("buku")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d("PerpuskuFragment", "${document.id} => ${document.data}")
                }
            }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}