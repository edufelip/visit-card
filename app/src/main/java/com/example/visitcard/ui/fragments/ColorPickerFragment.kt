package com.example.visitcard.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.visitcard.ui.adapters.ColorAdapter
import com.example.visitcard.databinding.FragmentColorPickerBinding

class ColorPickerFragment : Fragment(){
    private var _binding: FragmentColorPickerBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentColorPickerBinding.inflate(inflater, container, false)
        val view = binding.root

        val adapter = ColorAdapter()
        val recyclerview = binding.colorRecycler
        recyclerview.adapter = adapter
        recyclerview.layoutManager = GridLayoutManager(requireContext(), 3)

        adapter.setColorList(listOf("#145DA0", "#0C2D48", "#821D30", "#E43D40", "#F85C70", "#DC9750", "#922C40", "#345351", "#138B83"))

        setClickListeners()
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun setClickListeners() {
        binding.colorPickerBtnClose.setOnClickListener{
            findNavController().navigateUp()
        }
    }
}