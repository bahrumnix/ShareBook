package com.example.projectShareBook.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.projectShareBook.R
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projectShareBook.databinding.FragmentHomeBinding
import com.example.projectShareBook.databinding.FragmentPerpuskuBinding
import com.example.projectShareBook.ui.perpusku.PerpuskuViewModel


class HomeFragment : Fragment() {
    private lateinit var homeViewModel: HomeViewModel
    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        val bukuViewModel = HomeViewModel(BukuList())
        val bukuAdapter = BukuAdapter(bukuViewModel.getBukuku())

        val recyclerView =binding.recycleviewDicari
        recyclerView.adapter = bukuAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        bukuViewModel.getBukuku().observe(viewLifecycleOwner, Observer {
            recyclerView.adapter = bukuAdapter
        })

        val recyclerView2 =binding.recycleviewRekomendasi
        recyclerView2.adapter = bukuAdapter
        recyclerView2.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        bukuViewModel.getBukuku().observe(viewLifecycleOwner, Observer {
            recyclerView2.adapter = bukuAdapter
        })

       binding.viewProfile.setOnClickListener{
           Toast.makeText(requireContext(),"Masuk ke halaman profile",Toast.LENGTH_SHORT).show()
        }

        binding.btnBahasa.setOnClickListener{
            Toast.makeText(requireContext(), "Maaf, pilihan bahasa tidak tersedia", Toast.LENGTH_SHORT).show()
        }

        binding.btnSetting.setOnClickListener{
            Toast.makeText(requireContext(), "Maaf, pengaturan aplikasi tidak tersedia", Toast.LENGTH_SHORT).show()
        }

        binding.btnIconPlus.setOnClickListener{
            Toast.makeText(requireContext(), "Masuk ke halaman tambah buku", Toast.LENGTH_SHORT).show()
        }

        return binding.root
    }
}