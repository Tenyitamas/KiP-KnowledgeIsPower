package com.tenyitamas.kip_knowledgeispower.presentation.search

sealed class SearchEvent {

    data class OnQueryChange(val query: String) : SearchEvent()
    object OnSearch : SearchEvent()
    data class OnSearchFocusChange(val isFocused: Boolean): SearchEvent()
    object OnRefresh: SearchEvent()

}
