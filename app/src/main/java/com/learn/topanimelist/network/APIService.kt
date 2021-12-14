package com.learn.topanimelist.network

import com.learn.topanimelist.model.AnimeModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET


private const val BASE_URL = "https://api.jikan.moe/v3/"


// moshi converter

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

// retrofit restful api
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()


// HTTP request
interface AnimeAPIService {
    @GET("top/anime/1")
    suspend fun getProperties(): APIResponse
}

// singleton pattern for request
object AnimeAPI {
    val retrofitService : AnimeAPIService by lazy { retrofit.create(AnimeAPIService::class.java) }
}

// result json
@JsonClass(generateAdapter = true)
data class APIResponse(
    val top: List<AnimeAPIResponse>
)

@JsonClass(generateAdapter = true)
data class AnimeAPIResponse(
    val rank: String,
    val title: String,
    val url: String,
    @Json(name = "image_url") val imgUrl: String,
    val type: String,
    val score: String,
    @Json(name = "start_date") val date: String
)


// convert result json to model data class
fun APIResponse.asModel():List<AnimeModel>{
    return top.map {
        AnimeModel(
            rank = it.rank,
            title = it.title,
            url = it.url,
            imgUrl = it.imgUrl,
            type = it.type,
            score = it.score,
            date = it.date
        )
    }
}