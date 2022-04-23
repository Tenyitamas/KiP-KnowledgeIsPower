package com.tenyitamas.kip_knowledgeispower.domain.use_case

import com.tenyitamas.kip_knowledgeispower.domain.model.Article
import com.tenyitamas.kip_knowledgeispower.domain.repository.NewsRepository
import com.tenyitamas.kip_knowledgeispower.util.Resource

class GetNews(
    private val repository: NewsRepository
) {
    suspend operator fun invoke(countryCode: String, page: Int = 1): Resource<List<Article>> {
       return repository.getNews(countryCode, page)
    }
}