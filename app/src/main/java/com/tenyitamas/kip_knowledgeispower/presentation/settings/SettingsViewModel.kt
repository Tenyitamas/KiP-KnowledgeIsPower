package com.tenyitamas.kip_knowledgeispower.presentation.settings

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.tenyitamas.kip_knowledgeispower.domain.preferences.Preferences
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val preferences: Preferences
) : ViewModel() {
    var state by mutableStateOf(SettingsState())
        private set

    init {
        state = state.copy(
            countryCode = preferences.loadCountryCode()
        )
    }

    fun onEvent(event: SettingsEvent) {
        when(event) {
            is SettingsEvent.CountryCodeChange -> {
                state = state.copy(
                    countryCode = event.countryCode,
                    isCountryCodeExpanded = false
                )

                preferences.saveCountryCode(event.countryCode)
            }

            SettingsEvent.CountryCodeChooserClick -> {
                state = state.copy(
                    isCountryCodeExpanded = !state.isCountryCodeExpanded
                )
            }

            SettingsEvent.OnCountryCodeDismiss -> {
                state = state.copy(
                    isCountryCodeExpanded = false
                )
            }

        }
    }
}