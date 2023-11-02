package com.example.expance_room_mvvm

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.expance_room_mvvm.ExpanceTable

@Dao
interface ExpanceDoa {


    @Insert
    fun addExpance(newExpance : ExpanceTable)

    @Query("select * from expance")
    fun getAllExpance(): List<ExpanceTable>

    @Update
    fun updateExpance(updateExpance : ExpanceTable)

    @Delete
    fun deleteExpance(deleteExpance : ExpanceTable)


}