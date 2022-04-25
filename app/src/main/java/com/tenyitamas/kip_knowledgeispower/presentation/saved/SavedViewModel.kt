package com.tenyitamas.kip_knowledgeispower.presentation.saved

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tenyitamas.kip_knowledgeispower.domain.model.Article
import com.tenyitamas.kip_knowledgeispower.domain.use_case.NewsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SavedViewModel @Inject constructor(
    private val newsUseCases: NewsUseCases
) : ViewModel() {

    var state by mutableStateOf(SavedState())
        private set

    private var recentlyDeletedArticle: Article? = null

    init {
        refreshSavedNews()
    }

    fun onDeleteButtonClick(article: Article) {
        viewModelScope.launch {
            newsUseCases.deleteArticle(id = article.id ?: 0)
            recentlyDeletedArticle = article
        }.invokeOnCompletion {
            refreshSavedNews()
        }
    }

    fun onRestoreButtonClick() {
        viewModelScope.launch {
            recentlyDeletedArticle?.let {
                newsUseCases.saveArticle(it)
                recentlyDeletedArticle = null
            }
        }.invokeOnCompletion {
            refreshSavedNews()
        }
    }

    private fun refreshSavedNews() {
        viewModelScope.launch {
            newsUseCases.getSavedArticle()
                .collectLatest {
                    state = state.copy(
                        articles = it
                    )
                }

        }
    }



}