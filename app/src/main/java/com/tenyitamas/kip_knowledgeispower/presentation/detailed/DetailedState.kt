package com.tenyitamas.kip_knowledgeispower.presentation.detailed

import com.tenyitamas.kip_knowledgeispower.domain.model.Article

data class DetailedState(
    val article: Article? = null,
    val isSaved: Boolean = false
)
