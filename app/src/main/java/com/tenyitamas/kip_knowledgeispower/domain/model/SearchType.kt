package com.tenyitamas.kip_knowledgeispower.domain.model

sealed class SearchType(val name: String) {
    object TopHeadlines : SearchType("top_headlines")
    object AllNews : SearchType("all_news")

    companion object {
        fun fromString(name: String): SearchType {
            return when(name) {
                "top_headlines" -> TopHeadlines
                "all_news" -> AllNews
                else -> TopHeadlines

            }
        }
    }
}
