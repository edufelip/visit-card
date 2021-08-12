package com.example.visitcard.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.visitcard.data.CardDatabase
import com.example.visitcard.models.Card
import com.example.visitcard.repository.CardRepository
import com.example.visitcard.repository.ICardRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CardViewModel(application: Application): AndroidViewModel(application) {
    val cardList: LiveData<List<Card>>
    private val repo: ICardRepository

    init {
        val cardDao = CardDatabase.getDatabase(application).cardDao()
        repo = CardRepository(cardDao)
        cardList = repo.listCards()
    }

    fun addCard(card: Card) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.addCard(card)
        }
    }

    fun updateCard(card: Card) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.updateCard(card)
        }
    }

    fun deleteCard(card: Card) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.deleteCard(card)
        }
    }
}