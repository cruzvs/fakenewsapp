package com.cruz.fakenews.data

import com.cruz.fakenews.domain.News
import retrofit2.Call
import retrofit2.http.GET

interface NewsAPI {
    @get:GET("fakenews.json")
    val news: Call<List<News?>?>?
}