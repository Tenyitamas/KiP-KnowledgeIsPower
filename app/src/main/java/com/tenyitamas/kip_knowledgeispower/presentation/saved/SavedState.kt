package com.tenyitamas.kip_knowledgeispower.presentation.saved

import com.tenyitamas.kip_knowledgeispower.domain.model.Article

data class SavedState(
    val articles: List<Article> = emptyList()
)
