package com.example.visitcard.ui.fragments

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.visitcard.R
import com.example.visitcard.databinding.FragmentAddBinding
import com.example.visitcard.models.Card
import com.example.visitcard.ui.viewmodel.CardViewModel
import com.example.visitcard.ui.utils.MyMaskEditText

class AddFragment : Fragment() {
    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!

    private lateinit var mCardViewModel: CardViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddBinding.inflate(inflater, container, false)
        val view = binding.root

        findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<String>("data")?.observe(viewLifecycleOwner) { result ->
            binding.addFragmentColorPicker.background.setTint(Color.parseColor(result))
            binding.addFragmentColorHex.text = result
        }

        mCardViewModel = ViewModelProvider(this).get(CardViewModel::class.java)

        binding.addFragmentInnerTel.addTextChangedListener(MyMaskEditText(binding.addFragmentInnerTel, "(##) #####-####"))

        setClickListeners()
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setClickListeners() {
        binding.addFragmentBtnClose.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.addFragmentColorPicker.setOnClickListener {
            findNavController().navigate(R.id.action_addFragment_to_colorPickerFragment)
        }

        binding.addFragmentBtn.setOnClickListener {
            addToDatabase()
        }
    }

    private fun addToDatabase() {
        val name = binding.addFragmentInnerName.text.toString()
        val tel = binding.addFragmentInnerTel.text.toString()
        val email = binding.addFragmentInnerEmail.text.toString()
        val business = binding.addFragmentInnerBusiness.text.toString()
        val color = binding.addFragmentColorHex.text.toString()

        if(checkEmptyInputs(name, tel, email, business, color)) {
            Toast.makeText(requireContext(), "Por favor preencha todos os campos", Toast.LENGTH_SHORT).show()
            return
        }

        mCardViewModel.addCard(Card(0, name, tel, email, business, color))
        findNavController().navigateUp()
        Toast.makeText(requireContext(), "Cartão adicionado com sucesso", Toast.LENGTH_SHORT).show()
    }

    private fun checkEmptyInputs(name: String, tel: String, email: String, business: String, color: String): Boolean {
        return (name.isEmpty() || tel.isEmpty() || email.isEmpty() || business.isEmpty() || color.isEmpty())
    }
}