package com.example.recyclerview

import NewsAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.recyclerview.databinding.FragmentMainBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private val adapter = NewsAdapter(this::onTitleClick)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
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
