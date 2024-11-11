package com.dicoding.asclepius.view.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.asclepius.data.local.entity.HistoryEntity
import com.dicoding.asclepius.data.repository.HistoryRepository
import kotlinx.coroutines.launch

class HistoryViewModel(private val repository: HistoryRepository) : ViewModel() {

    val history: LiveData<List<HistoryEntity>> = repository.getHistory()


    fun insertHistory(history: List<HistoryEntity>) = viewModelScope.launch {
        repository.insertHistory(history)
    }

    fun deleteHistory(history: HistoryEntity) = viewModelScope.launch {
        repository.deleteHistory(history)
    }
}