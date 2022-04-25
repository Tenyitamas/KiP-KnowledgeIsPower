package com.tenyitamas.kip_knowledgeispower.presentation.detailed

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.content.ContextCompat.startActivity
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

    val TAG = "DetailsViewModel"

    var state by mutableStateOf(DetailedState())
        private set
    var articleId = 0;

    init {
        Log.d(TAG, "init: ...")
        state = state.copy(
            article = savedStateHandle.get<Article>("article")
        )
        articleId = state.article?.id ?: 0
        refreshSavedNews()

    }

    private fun refreshSavedNews() {
        Log.d(TAG, "refreshSavedNews: stateArticleId: ${state.article?.id}")
        viewModelScope.launch {
            newsUseCases.getSavedArticle()
                .collectLatest {
                    state = state.copy(
                        isSaved = doesContainArticle(it, state.article),
                        isLoaded = true
                    )
                }

        }
    }

    private fun doesContainArticle(it: List<Article>, article: Article?): Boolean {
        it.forEach {

            if(it.title ?: "abc" == article?.title ?: "cde") {
                Log.d(TAG, "doesContainArticle: ${it.id}")
                articleId = it.id ?: 0
                return true
            }
        }

        return false
    }

    fun onEvent(event: DetailedEvent) {
        when(event) {
            DetailedEvent.OnSaveButtonClick -> {
                if(state.isSaved) {
                    deleteArticleFromSavedArticles(articleId)
                } else {
                    insertArticleToSavedArticles(state.article)
                }
            }

        }
    }

    private fun insertArticleToSavedArticles(article: Article?) {
        article?.let {
            viewModelScope.launch {
                newsUseCases.saveArticle(article)

            }.invokeOnCompletion {
                refreshSavedNews()
            }
        }
    }

    private fun deleteArticleFromSavedArticles(id: Int) {
        viewModelScope.launch {
            Log.d("DetailsViewModel", "deleteArticleFromSavedArticles: $id")
            newsUseCases.deleteArticle(id = id)
        }.invokeOnCompletion {
            refreshSavedNews()
        }
    }
}