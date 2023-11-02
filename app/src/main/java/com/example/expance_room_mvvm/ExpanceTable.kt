package com.example.expance_room_mvvm

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "expance")
data class ExpanceTable(
    @PrimaryKey(autoGenerate = true)
    val id : Int,


    val title : String,
    val desc: String,
    val amoount: String,
    val date: String,
    val addCatorgy: String,
    val amtExpance: String
)
