package com.example.recyclerview

import NewsAdapter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.recyclerview.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val adapter = NewsAdapter(this::onTitleClick)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupData()
    }

    private fun setupData() {
        binding.recyclerViewNews.adapter = adapter
        adapter.setData(getNews())
    }

    private fun getNews(): List<News> {
        val news = mutableListOf<News>()
        for (i in 0..10) {
            news.add(News("Title $i", "This is content of news $i"))
        }
        return news
    }

    private fun onTitleClick(news: News, position: Int) {
        news.isExpanded = !news.isExpanded
        adapter.notifyItemChanged(position)
    }
}
