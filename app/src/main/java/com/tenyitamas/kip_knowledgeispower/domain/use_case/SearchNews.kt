package com.tenyitamas.kip_knowledgeispower.domain.use_case

import com.tenyitamas.kip_knowledgeispower.domain.model.Article
import com.tenyitamas.kip_knowledgeispower.domain.repository.NewsRepository
import com.tenyitamas.kip_knowledgeispower.util.Resource

class SearchNews(
    private val repository: NewsRepository
) {
    suspend operator fun invoke(query: String, page: Int = 1): Resource<List<Article>> {
        return repository.searchNews(query, page)
    }
}