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

    private val url = animeModel.url
    private val title = animeModel.title
    private val rating = animeModel.score
    private val rank = animeModel.rank

    val displayRank = "Rank #$rank"

    // Initialize the _selectedAnime MutableLiveData
    init {
        _selectedAnime.value = animeModel
    }

    // for share intent
    private fun onShare(){
        val subject = "Watch this ${title} Rank ${rank}"
        val body = "Watch this now!!!\n" +
                "This anime \"${title}\" is amazing with $rating rating. " +
                "The rank is $rank in all anime." +
                "\n\nThis data get from MyAnimeList $url"

        val shareIntent = Intent(Intent.ACTION_SEND)
            .setType("text/plain")
            .putExtra(Intent.EXTRA_SUBJECT, subject )
            .putExtra(Intent.EXTRA_TEXT, body)
        ContextCompat.startActivity(getApplication(), shareIntent, null)
    }

    fun onShareClicked(){
        onShare()
    }

}