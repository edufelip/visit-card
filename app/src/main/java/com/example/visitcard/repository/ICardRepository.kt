package com.example.visitcard.repository

import androidx.lifecycle.LiveData
import com.example.visitcard.models.Card

interface ICardRepository {
    fun listCards(): LiveData<List<Card>>

    suspend fun addCard(card: Card)

    suspend fun updateCard(card: Card)

    suspend fun deleteCard(card: Card)
}