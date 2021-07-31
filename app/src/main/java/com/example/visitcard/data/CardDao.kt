package com.example.visitcard.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.visitcard.domain.models.Card

@Dao
interface CardDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addCard(card: Card)

    @Query("SELECT * FROM cards ORDER BY id ASC")
    fun listCards(): LiveData<List<Card>>

    @Update
    suspend fun updateCard(card: Card)

    @Delete
    suspend fun deleteCard(card: Card)
}