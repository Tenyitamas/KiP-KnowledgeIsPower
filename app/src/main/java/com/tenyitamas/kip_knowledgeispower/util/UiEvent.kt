package com.tenyitamas.kip_knowledgeispower.util

sealed class UiEvent {
    object Success: UiEvent()
    object NavigateUp: UiEvent()

    data class ShowSnackBar(val message: String): UiEvent()
}
