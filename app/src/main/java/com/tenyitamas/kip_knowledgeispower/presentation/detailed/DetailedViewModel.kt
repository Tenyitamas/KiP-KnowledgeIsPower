package com.tenyitamas.kip_knowledgeispower.presentation.detailed

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tenyitamas.kip_knowledgeispower.domain.model.Article
import com.tenyitamas.kip_knowledgeispower.domain.use_case.NewsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailedViewModel @Inject constructor(
    private val newsUseCases: NewsUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    var state by mutableStateOf(DetailedState())
        private set

    init {
        viewModelScope.launch {
            newsUseCases.getSavedArticle()
                .collectLatest {
                    state = state.copy(
                        isSaved = it.contains(state.article),
                        article = savedStateHandle.get<Article>("article")
                    )
                }
        }
    }

    fun onEvent(event: DetailedEvent) {
        when(event) {
            DetailedEvent.OnSaveButtonClick -> {
                if(state.isSaved) {
                    deleteArticleFromSavedArticles(state.article)
                } else {
                    insertArticleToSavedArticles(state.article)
                }
            }
            DetailedEvent.OnShareButtonClick -> {

            }
        }
    }

    private fun insertArticleToSavedArticles(article: Article?) {
        article?.let {
            viewModelScope.launch {
                newsUseCases.saveArticle(article)
                state = state.copy(
                    isSaved = true
                )
            }
        }
    }

    private fun deleteArticleFromSavedArticles(article: Article?) {
        article?.let {
            viewModelScope.launch {
                newsUseCases.deleteArticle(article = article)
                state = state.copy(
                    isSaved = false
                )
            }
        }
    }
}