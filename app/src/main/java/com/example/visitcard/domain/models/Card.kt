package com.example.visitcard.domain.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "cards")
data class Card(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val tel: String,
    val email: String,
    val business: String,
    val color: String
    ) : Parcelable {
}