package com.tenyitamas.kip_knowledgeispower.presentation.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.Navigation.findNavController
import com.tenyitamas.kip_knowledgeispower.domain.preferences.Preferences
import com.tenyitamas.kip_knowledgeispower.domain.use_case.NewsUseCases
import com.tenyitamas.kip_knowledgeispower.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val newsUseCases: NewsUseCases,
    private val preferences: Preferences
) : ViewModel() {

    var state by mutableStateOf(SearchState())
        private set


    init {


        state = state.copy(
            countryCode = preferences.loadCountryCode()
        )
        refreshNews()
    }
    fun onEvent(event: SearchEvent) {
        when (event) {
            is SearchEvent.OnQueryChange -> {
                state = state.copy(
                    query = event.query
                )
            }
            SearchEvent.OnRefresh -> {
                state = state.copy(
                    query = "",
                    isHintVisible = true
                )
                refreshNews()

            }
            SearchEvent.OnSearch -> {
                searchForNews()
            }
            is SearchEvent.OnSearchFocusChange -> {
                state = state.copy(
                    isHintVisible = !event.isFocused && state.query.isBlank()
                )
            }
        }
    }

    private fun searchForNews() {
        viewModelScope.launch {
            val res = newsUseCases.searchNews(state.query)
            when(res) {
                is Resource.Error -> {
                    // Show toast message
                }
                is Resource.Loading -> {
                    // Show loading indicator
                }
                is Resource.Success -> {
                    state = state.copy(
                        articles = res.data ?: emptyList()
                    )
                }
            }
        }
    }

    private fun refreshNews() {
        viewModelScope.launch {
            val res = newsUseCases.getTopNews(
                countryCode = state.countryCode
            )

            when(res) {
                is Resource.Error -> {
                    // Show toast msg
                    return@launch
                }
                is Resource.Loading -> {
                    // Show indicator
                }
                is Resource.Success -> {
                    state = state.copy(
                        articles = res.data ?: emptyList()
                    )
                }
            }
        }
    }
}