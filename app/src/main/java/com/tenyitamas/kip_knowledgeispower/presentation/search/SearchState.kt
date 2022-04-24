package com.tenyitamas.kip_knowledgeispower.presentation.search

import com.tenyitamas.kip_knowledgeispower.domain.model.Article

data class SearchState(
    val query: String = "",
    val isHintVisible: Boolean = true,
    val isSearching: Boolean = false,
    val articles: List<Article> = emptyList(),
    val countryCode: String = "us"
)
