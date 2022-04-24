package com.tenyitamas.kip_knowledgeispower.data.preferences

import android.content.SharedPreferences
import com.tenyitamas.kip_knowledgeispower.domain.preferences.Preferences

class DefaultPreferences(
    private val sharedPref: SharedPreferences
): Preferences {
    override fun saveCountryCode(countryCode: String) {
        sharedPref.edit()
            .putString(Preferences.KEY_COUNTRY_CODE, countryCode)
            .apply()
    }

    override fun loadCountryCode(): String {
        return sharedPref.getString(Preferences.KEY_COUNTRY_CODE, null) ?: "us"
    }


}