package com.tenyitamas.kip_knowledgeispower.presentation.settings


sealed class SettingsEvent {
    data class CountryCodeChange(val countryCode: String): SettingsEvent()

    object CountryCodeChooserClick: SettingsEvent()

    object OnCountryCodeDismiss: SettingsEvent()
}
