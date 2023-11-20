package com.example.expance_room_mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class ExpenseViewModal(val repository: ExpanceRepository) : ViewModel() {

    fun addExpace(newExpance: ExpanceTable){
        repository.addExpance(newExpance)
    }

    fun getAllExpances(): LiveData<List<ExpanceTable>>{
        return repository.getAllExpance()
    }
}