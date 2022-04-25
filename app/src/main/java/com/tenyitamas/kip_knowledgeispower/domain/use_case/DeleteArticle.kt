package com.tenyitamas.kip_knowledgeispower.domain.use_case

import com.tenyitamas.kip_knowledgeispower.domain.model.Article
import com.tenyitamas.kip_knowledgeispower.domain.repository.NewsRepository

class DeleteArticle(
    private val repository: NewsRepository
) {
    suspend operator fun invoke(id: Int) {
        repository.deleteArticle(id)
    }
}