package com.example.expance_room_mvvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ExpanceViewModalFactory(val repository: ExpanceRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ExpenseViewModal(repository) as T
    }
}