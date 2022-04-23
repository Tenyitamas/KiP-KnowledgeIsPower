package com.tenyitamas.kip_knowledgeispower.domain.preferences

import com.tenyitamas.kip_knowledgeispower.domain.model.SearchType
import com.tenyitamas.kip_knowledgeispower.domain.model.SettingsInfo


interface Preferences {
    fun saveCountryCode(countryCode: String)
    fun saveSearchType(searchType: SearchType)

    fun loadSettingsInfo(): SettingsInfo

    companion object {
        const val KEY_COUNTRY_CODE = "country_code"
        const val KEY_SEARCH_TYPE = "search_type"
    }
}