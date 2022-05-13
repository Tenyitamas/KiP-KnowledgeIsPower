@file:OptIn(ExperimentalCoilApi::class)

package com.tenyitamas.kip_knowledgeispower.presentation.search

import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import coil.annotation.ExperimentalCoilApi
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.tenyitamas.kip_knowledgeispower.R
import com.tenyitamas.kip_knowledgeispower.domain.model.Article
import com.tenyitamas.kip_knowledgeispower.presentation.search.components.SearchTextField
import com.tenyitamas.kip_knowledgeispower.presentation.shared.NewsItem
import com.tenyitamas.kip_knowledgeispower.ui.theme.LocalSpacing

@Composable
fun SearchScreen(
    onArticleClick: (Article) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel = hiltViewModel()
) {

    val spacing = LocalSpacing.current
    val state = viewModel.state
    val context = LocalContext.current

    val swipeRefreshState = rememberSwipeRefreshState(false)
    SwipeRefresh(
        state = swipeRefreshState,
        onRefresh = {
            viewModel.onEvent(SearchEvent.OnRefresh)
        },
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {
        LazyColumn {
            item {
                SearchTextField(
                    text = viewModel.state.query,
                    onValueChange = {
                        viewModel.onEvent(
                            SearchEvent.OnQueryChange(it)
                        )
                    },
                    onSearch = {
                        viewModel.onEvent(SearchEvent.OnSearch)
                    },
                    onFocusChanged = {
                        viewModel.onEvent(SearchEvent.OnSearchFocusChange(it.isFocused))
                    },
                    shouldShowHint = state.isHintVisible
                )
            }

            if(state.articles.isEmpty()) {
                item {
                    Text(
                        text = stringResource(id = R.string.no_articles),
                        style = MaterialTheme.typography.h4,
                        color = MaterialTheme.colors.primary,
                        modifier = Modifier.fillMaxSize(),
                        textAlign = TextAlign.Center,
                        )
                }
            }

            items(viewModel.state.articles) { article ->
                NewsItem(
                    article = article,
                    onClick = { onArticleClick(article) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(spacing.spaceSmall)
                )
            }

        }

    }
}