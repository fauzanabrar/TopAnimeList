package com.learn.topanimelist.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class AnimeModel(val rank: String,
                      val title: String,
                      val url: String,
                      val imgUrl: String,
                      val type: String,
                      val score: String,
                      val date: String): Parcelable