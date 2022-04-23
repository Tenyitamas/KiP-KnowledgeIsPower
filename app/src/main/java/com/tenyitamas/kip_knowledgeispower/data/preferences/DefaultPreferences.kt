package com.tenyitamas.kip_knowledgeispower.data.preferences

import android.content.SharedPreferences
import com.tenyitamas.kip_knowledgeispower.domain.model.SearchType
import com.tenyitamas.kip_knowledgeispower.domain.model.SettingsInfo
import com.tenyitamas.kip_knowledgeispower.domain.preferences.Preferences

class DefaultPreferences(
    private val sharedPref: SharedPreferences
): Preferences {
    override fun saveCountryCode(countryCode: String) {
        sharedPref.edit()
            .putString(Preferences.KEY_COUNTRY_CODE, countryCode)
            .apply()
    }

    override fun saveSearchType(searchType: SearchType) {
        sharedPref.edit()
            .putString(Preferences.KEY_SEARCH_TYPE, searchType.name)
            .apply()
    }

    override fun loadSettingsInfo(): SettingsInfo {
        val countryCode =sharedPref.getString(Preferences.KEY_COUNTRY_CODE, null)
        val searchTypeString = sharedPref.getString(Preferences.KEY_SEARCH_TYPE, null)
        return SettingsInfo(
            searchType = SearchType.fromString(searchTypeString ?: "top_headlines"),
            countryCode = countryCode ?: "us"
        )
    }
}