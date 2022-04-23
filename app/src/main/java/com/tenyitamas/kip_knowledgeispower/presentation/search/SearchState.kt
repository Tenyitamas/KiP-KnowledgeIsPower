package com.tenyitamas.kip_knowledgeispower.presentation.search

import com.tenyitamas.kip_knowledgeispower.domain.model.Article
import com.tenyitamas.kip_knowledgeispower.domain.model.SearchType
import com.tenyitamas.kip_knowledgeispower.domain.model.SettingsInfo

data class SearchState(
    val query: String = "",
    val isHintVisible: Boolean = true,
    val isSearching: Boolean = false,
    val articles: List<Article> = emptyList(),
    val settingsInfo: SettingsInfo = SettingsInfo(
        searchType = SearchType.TopHeadlines,
        countryCode = "us"
    )
)
