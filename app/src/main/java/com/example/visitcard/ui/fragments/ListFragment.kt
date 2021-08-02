package com.example.visitcard.ui.fragments

import android.opengl.Visibility
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.visitcard.R
import com.example.visitcard.ui.adapters.ListAdapter
import com.example.visitcard.databinding.FragmentListBinding
import com.example.visitcard.domain.models.Card
import com.example.visitcard.ui.viewmodel.CardViewModel

class ListFragment : Fragment() {
    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    private lateinit var mCardViewModel: CardViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        val view = binding.root

        // Set recyclerview
        val adapter = ListAdapter()
        val recyclerView = binding.listRecycler
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // CardViewModel
        mCardViewModel = ViewModelProvider(this).get(CardViewModel::class.java)
        mCardViewModel.cardList.observe(viewLifecycleOwner, { cards ->
            adapter.setCardList(cards)
            if(cards.isEmpty()) {
                binding.noImageContainer.visibility = View.VISIBLE
            } else {
                binding.noImageContainer.visibility = View.GONE
            }
        })

        setClickListeners()
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setClickListeners() {
        binding.addFab.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }
    }
}