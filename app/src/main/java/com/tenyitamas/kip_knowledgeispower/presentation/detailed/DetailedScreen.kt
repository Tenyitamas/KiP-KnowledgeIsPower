package com.tenyitamas.kip_knowledgeispower.presentation.detailed

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.tenyitamas.kip_knowledgeispower.domain.model.Article
import com.tenyitamas.kip_knowledgeispower.presentation.detailed.components.ArticleHeader
import com.tenyitamas.kip_knowledgeispower.ui.theme.LocalSpacing

@Composable
fun DetailedScreen(
    article: Article?,
    viewModel: DetailedViewModel = hiltViewModel()
) {
    val state = viewModel.state
    val spacing = LocalSpacing.current

    Column(
        modifier = Modifier
            .fillMaxSize()

    ) {
        ArticleHeader(
            modifier = Modifier
                .background(MaterialTheme.colors.surface),
            title = state.article?.title ?: "Unknown title",
            source = state.article?.author ?: "N/A",
            timeString = state.article?.publishedAt ?: "N/A"
        )
    }
}