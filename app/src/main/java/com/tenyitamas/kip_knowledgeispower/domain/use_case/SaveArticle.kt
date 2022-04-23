package com.tenyitamas.kip_knowledgeispower.domain.use_case

import com.tenyitamas.kip_knowledgeispower.domain.model.Article
import com.tenyitamas.kip_knowledgeispower.domain.repository.NewsRepository

class SaveArticle(
    private val repository: NewsRepository
) {
    suspend operator fun invoke(article: Article) {
        repository.saveArticle(article)
    }
}