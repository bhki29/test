package com.dicoding.asclepius.di

import android.content.Context
import com.dicoding.asclepius.data.local.room.HistoryDatabase
import com.dicoding.asclepius.data.repository.HistoryRepository
import com.dicoding.asclepius.data.repository.NewsRepository
import com.dicoding.asclepius.data.retrofit.ApiConfig

object Injection {
    fun provideNewsRepository(context: Context): NewsRepository {
        val apiService = ApiConfig.getApiService()
        return NewsRepository.getInstance(apiService)
    }

    fun provideHistoryRepository(context: Context): HistoryRepository {
        val database = HistoryDatabase.getInstance(context)
        val dao = database.historyDao()

        return HistoryRepository.getInstance(dao)
    }
}