package com.cruz.fakenews.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.cruz.fakenews.databinding.ActivityNewsBinding
import com.cruz.fakenews.domain.News


class NewsActivity : AppCompatActivity() {


    object Extra{
        const val NEWS = "EXTRA_NEWS"
    }

    private lateinit var binding: ActivityNewsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadNewsFromExtra()
    }

    private fun loadNewsFromExtra(){
        intent?.extras?.getParcelable<News>(Extra.NEWS)?.let {
            Glide.with(this).load(it.image).into(binding.imgNews)
            binding.newsTitle.text = it.title
            binding.contentNews.text = it.news

        }
    }


}