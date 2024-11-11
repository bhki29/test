package com.dicoding.asclepius.view.analyze

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AnalyzeViewModel : ViewModel() {
    private val _currentImageUri = MutableLiveData<Uri?>()
    val currentImageUri: LiveData<Uri?> = _currentImageUri

    fun setImageUri(uri: Uri?) {
        _currentImageUri.value = uri
    }

    fun getImageUri(): Uri? {
        return _currentImageUri.value
    }
}