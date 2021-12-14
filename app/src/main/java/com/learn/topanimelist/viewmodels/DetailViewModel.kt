package com.learn.topanimelist.viewmodels

import android.app.Application
import android.content.Intent
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.learn.topanimelist.model.AnimeModel

class DetailViewModel(animeModel: AnimeModel, app: Application): AndroidViewModel(app) {

    private val _selectedAnime = MutableLiveData<AnimeModel>()
    val selectedAnime: LiveData<AnimeModel>
        get() = _selectedAnime

    // Initialize the _selectedAnime MutableLiveData
    init {
        _selectedAnime.value = animeModel
    }


}