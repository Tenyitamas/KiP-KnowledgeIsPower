package com.tenyitamas.kip_knowledgeispower.domain.use_case

import com.tenyitamas.kip_knowledgeispower.domain.model.Article
import com.tenyitamas.kip_knowledgeispower.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class GetSavedArticle(
    private val repository: NewsRepository
) {
    operator fun invoke(): Flow<List<Article>> {
        return repository.getSavedArticles()
    }
}