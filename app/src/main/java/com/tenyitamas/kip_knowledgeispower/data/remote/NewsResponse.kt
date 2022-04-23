package com.tenyitamas.kip_knowledgeispower.data.remote

import com.tenyitamas.kip_knowledgeispower.domain.model.Article

data class NewsResponse(
    val articles: MutableList<Article>,
    val status: String,
    val totalResults: Int
)