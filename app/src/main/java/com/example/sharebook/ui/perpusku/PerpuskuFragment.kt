package com.example.sharebook.ui.perpusku

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.sharebook.R


class PerpuskuFragment : Fragment() {
    private lateinit var perpuskuViewModel: PerpuskuViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        perpuskuViewModel =
            ViewModelProvider(this).get(PerpuskuViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_perpusku, container, false)
        val textView: TextView = root.findViewById(R.id.text_mylibrary)
        perpuskuViewModel.text.observe(viewLifecycleOwner, {
            textView.text = it
        })
        return root
    }
}