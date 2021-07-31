package com.example.visitcard.domain.repository

import androidx.lifecycle.LiveData
import com.example.visitcard.data.CardDao
import com.example.visitcard.domain.models.Card

class CardRepository(private val cardDao: CardDao) {
    val listCards: LiveData<List<Card>> = cardDao.listCards()

    suspend fun addCard(card: Card) {
        cardDao.addCard(card)
    }

    suspend fun updateCard(card: Card) {
        cardDao.updateCard(card)
    }

    suspend fun deleteCard(card: Card) {
        cardDao.deleteCard(card)
    }
}