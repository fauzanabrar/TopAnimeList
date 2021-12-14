package com.learn.topanimelist.viewmodels

import android.accounts.NetworkErrorException
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.learn.topanimelist.model.AnimeModel
import com.learn.topanimelist.network.AnimeAPI
import com.learn.topanimelist.network.asModel
import kotlinx.coroutines.launch

// for tell view if there are network problem
enum class AnimeAPIStatus { LOADING, ERROR, DONE }

class OverviewViewModel: ViewModel() {

    private val _status = MutableLiveData<AnimeAPIStatus>()
    val status: LiveData<AnimeAPIStatus>
        get() = _status

    private val _animeList = MutableLiveData<List<AnimeModel>>()
    val animeList: LiveData<List<AnimeModel>>
        get() = _animeList

    // LiveData to handle navigation to the selected anime
    private val _navigateToSelectedAnime = MutableLiveData<AnimeModel>()
    val navigateToSelectedAnime: LiveData<AnimeModel>
        get() = _navigateToSelectedAnime


    init {
        getAnimeList()
    }


    private fun getAnimeList() {
        viewModelScope.launch {
            _status.value = AnimeAPIStatus.LOADING
            try {
                _animeList.value = AnimeAPI.retrofitService.getProperties().asModel()
                _status.value = AnimeAPIStatus.DONE
            } catch (e: NetworkErrorException) {
                _status.value = AnimeAPIStatus.ERROR
                _animeList.value = ArrayList()
            }
        }
    }

    fun displayAnimeDetails(animeModel: AnimeModel) {
        _navigateToSelectedAnime.value = animeModel
    }

    fun displayAnimeDetailsDone() {
        _navigateToSelectedAnime.value = null
    }


}