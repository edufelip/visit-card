package com.example.visitcard.ui.fragments

import android.app.AlertDialog
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.visitcard.R
import com.example.visitcard.databinding.FragmentEditBinding
import com.example.visitcard.models.Card
import com.example.visitcard.ui.viewmodel.CardViewModel

class EditFragment : Fragment() {
    private var _binding: FragmentEditBinding? = null
    private val binding get() = _binding!!
    private val args by navArgs<EditFragmentArgs>()

    private lateinit var mCardViewModel: CardViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEditBinding.inflate(inflater, container, false)
        val view = binding.root
        setInputWithArgs()

        mCardViewModel = ViewModelProvider(this).get(CardViewModel::class.java)

        findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<String>("data")?.observe(viewLifecycleOwner) { result ->
            binding.editFragmentColorPicker.background.setTint(Color.parseColor(result))
            binding.editFragmentColorHex.text = result
        }

        setClickListeners()
        return view
    }

    private fun deleteCard() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Sim") { _, _ ->
            mCardViewModel.deleteCard(args.card)
            Toast.makeText(requireContext(), "Cartão deletado com sucesso!", Toast.LENGTH_SHORT).show()
            findNavController().navigateUp()
        }
        builder.setNegativeButton("Não") { _, _ ->
        }
        builder.setTitle("Deseja mesmo deletar esse cartão?")
        builder.setMessage("Saiba que não poderá recuperá-lo depois")
        builder.create().show()
    }

    private fun setInputWithArgs() {
        binding.editFragmentInputName.editText?.setText(args.card.name)
        binding.editFragmentInputEmail.editText?.setText(args.card.email)
        binding.editFragmentInputTelephone.editText?.setText(args.card.tel)
        binding.editFragmentInputBusiness.editText?.setText(args.card.business)
        binding.editFragmentColorPicker.background.setTint(Color.parseColor(args.card.color))
    }

    private fun updateCard() {
        val id = args.card.id
        val name = binding.editFragmentInnerName.text.toString()
        val tel = binding.editFragmentInnerTel.text.toString()
        val email = binding.editFragmentInnerEmail.text.toString()
        val business = binding.editFragmentInnerBusiness.text.toString()
        val color = binding.editFragmentColorHex.text.toString()

        if(checkEmptyInputs(name, tel, email, business, color)) {
            Toast.makeText(requireContext(), "Por favor preencha todos os campos", Toast.LENGTH_SHORT).show()
            return
        }

        mCardViewModel.updateCard(Card(id, name, tel, email, business, color))
        findNavController().navigateUp()
    }

    private fun setClickListeners() {
        binding.editFragmentBtnClose.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.editFragmentColorPicker.setOnClickListener {
            findNavController().navigate(R.id.action_editFragment_to_colorPickerFragment)
        }

        binding.editFragmentEditBtn.setOnClickListener {
            updateCard()
        }

        binding.editFragmentBtnDelete.setOnClickListener {
            deleteCard()
        }
    }

    private fun checkEmptyInputs(name: String, tel: String, email: String, business: String, color: String): Boolean {
        return (name.isEmpty() || tel.isEmpty() || email.isEmpty() || business.isEmpty() || color.isEmpty())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}