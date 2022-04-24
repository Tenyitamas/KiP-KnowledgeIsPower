package com.tenyitamas.kip_knowledgeispower.presentation.settings


data class SettingsState(
    val countryCode: String = "us",
    val isCountryCodeExpanded: Boolean = false,
    val isSearchTypeExpanded: Boolean = false
) {
    companion object {
        val countryCodes = listOf<String>(
            "us",
            "fr",
            "de",
            "it",
            "es"
        )
    }
}
