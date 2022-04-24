package com.tenyitamas.kip_knowledgeispower.presentation.detailed

sealed class DetailedEvent {
    object OnSaveButtonClick: DetailedEvent()
    object OnShareButtonClick: DetailedEvent()
}
