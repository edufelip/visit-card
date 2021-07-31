package com.example.visitcard.ui.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.visitcard.databinding.RecyclerColorBoxBinding

class ColorAdapter(): RecyclerView.Adapter<ColorAdapter.MyViewHolder>() {
    private var colorList = listOf<String>()

    class MyViewHolder(private val itemBinding: RecyclerColorBoxBinding): RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(color: String) {
            itemBinding.colorHex.text = color
            itemBinding.colorBox.background.setTint(Color.parseColor(color))
            itemBinding.colorBox.setOnClickListener {
                Navigation.findNavController(itemBinding.root).previousBackStackEntry?.savedStateHandle?.set("data", color)
                Navigation.findNavController(itemBinding.root).navigateUp()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = RecyclerColorBoxBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val color: String = colorList[position]
        holder.bind(color)
    }

    override fun getItemCount(): Int {
        return colorList.size
    }

    fun setColorList(colors: List<String>) {
        this.colorList = colors
        notifyDataSetChanged()
    }
}