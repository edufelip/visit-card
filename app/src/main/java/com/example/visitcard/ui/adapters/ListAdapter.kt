package com.example.visitcard.ui.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.visitcard.databinding.RecyclerBusinessCardBinding
import com.example.visitcard.domain.models.Card
import com.example.visitcard.ui.fragments.ListFragmentDirections

class ListAdapter: RecyclerView.Adapter<ListAdapter.MyViewHolder>() {
    private var cardList = listOf<Card>()

    class MyViewHolder(private val itemBinding: RecyclerBusinessCardBinding): RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(card: Card) {
            itemBinding.itemName.text = card.name
            itemBinding.itemEmail.text = card.email
            itemBinding.itemTelephone.text = card.tel
            itemBinding.itemBusiness.text = card.business
            itemBinding.cardContainer.background.setTint(Color.parseColor(card.color))
            itemBinding.cardContainer.setOnClickListener {
                val action = ListFragmentDirections.actionListFragmentToEditFragment(card)
                Navigation.findNavController(itemBinding.root).navigate(action)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = RecyclerBusinessCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val card: Card = cardList[position]
        holder.bind(card)
    }

    override fun getItemCount(): Int {
        return cardList.size
    }

    fun setCardList(cards: List<Card>) {
        this.cardList = cards
        notifyDataSetChanged()
    }

}