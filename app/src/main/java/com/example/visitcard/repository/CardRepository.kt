package com.example.visitcard.repository

import androidx.lifecycle.LiveData
import com.example.visitcard.data.CardDao
import com.example.visitcard.models.Card

class CardRepository(private val cardDao: CardDao): ICardRepository {
    override fun listCards(): LiveData<List<Card>> {
        return cardDao.listCards()
    }

    override suspend fun addCard(card: Card) {
        cardDao.addCard(card)
    }

    override suspend fun updateCard(card: Card) {
        cardDao.updateCard(card)
    }

    override suspend fun deleteCard(card: Card) {
        cardDao.deleteCard(card)
    }
}