package com.dicoding.asclepius.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.dicoding.asclepius.BuildConfig
import com.dicoding.asclepius.data.remote.Result
import com.dicoding.asclepius.data.response.ArticlesItem
import com.dicoding.asclepius.data.retrofit.ApiService


class NewsRepository private constructor(private val apiService: ApiService) {

    fun fetchNews(): LiveData<Result<List<ArticlesItem>>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.getNews(
                query = "history",
                category = "health",
                language = "en",
                apiKey = BuildConfig.API_KEY
            )
            val filteredArticles =
                response.articles.filter {
                    it.author != null && it.urlToImage != null
                }

            emit(Result.Success(filteredArticles))
        } catch (e: Exception) {
            Log.d(TAG, "NewsRepository: ${e.message.toString()} ")
            emit(Result.Error(e.message.toString()))
        }
    }

    companion object {
        const val TAG = "NewsRepository"

        @Volatile
        private var instance: NewsRepository? = null
        fun getInstance(
            apiService: ApiService,

            ): NewsRepository =
            instance ?: synchronized(this) {
                instance ?: NewsRepository(apiService)
            }.also { instance = it }
    }
}