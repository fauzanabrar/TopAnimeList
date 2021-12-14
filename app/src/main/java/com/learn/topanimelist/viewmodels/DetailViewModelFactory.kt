package com.learn.topanimelist.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.learn.topanimelist.model.AnimeModel

class DetailViewModelFactory(
    private val animeModel: AnimeModel,
    private val app: Application
    ): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(animeModel, app) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}