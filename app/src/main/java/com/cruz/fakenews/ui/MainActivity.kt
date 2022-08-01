package com.cruz.fakenews.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.cruz.fakenews.R
import com.cruz.fakenews.ui.adapter.NewsAdapter
import com.cruz.fakenews.data.NewsAPI
import com.cruz.fakenews.databinding.ActivityMainBinding
import com.cruz.fakenews.databinding.ActivityNewsBinding
import com.cruz.fakenews.databinding.CardNewsBinding
import com.cruz.fakenews.domain.News
import com.cruz.fakenews.ui.NewsActivity.Extra.NEED_KEY
import com.cruz.fakenews.ui.NewsActivity.Extra.THE_LIKE
import com.google.android.material.snackbar.Snackbar
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {


    private lateinit var newsApi :  NewsAPI
    private lateinit var binding: ActivityMainBinding
    private var newsAdapter = NewsAdapter(emptyList())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupHttpClient()
        newsList()
        newsReflesh()
    }

    private fun setupHttpClient(){
        val retrofit = Retrofit.Builder()
            .baseUrl("https://cruzvs.github.io/fake_news_api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        newsApi = retrofit.create(NewsAPI :: class.java)
    }

    private fun newsList(){
        binding.rwScreen.setHasFixedSize(true)
        binding.rwScreen.layoutManager = LinearLayoutManager(this)
        binding.rwScreen.adapter = newsAdapter
        findNewsFromApi()
    }

    private fun newsReflesh(){
        binding.swipe.setOnRefreshListener(this::findNewsFromApi)
    }

    private fun findNewsFromApi(){
        binding.swipe.isRefreshing = true
        newsApi.news?.enqueue(object:Callback<List<News?>?> {
            override fun onResponse(call: Call<List<News?>?>, response: Response<List<News?>?>) {
                if(response.isSuccessful){
                    val news : List<News?>? = response.body()
                    newsAdapter = NewsAdapter(news)
                    binding.rwScreen.adapter = newsAdapter
                }else{
                    showMessage()
                }
            }
            override fun onFailure(call: Call<List<News?>?>, t: Throwable) {
                Toast.makeText(this@MainActivity,call.toString(),Toast.LENGTH_LONG).show()
            }
        })
        binding.swipe.isRefreshing = false
    }



    private fun showMessage(){
        Snackbar.make(binding.root, R.string.error_api, Snackbar.LENGTH_LONG).show()
    }
}