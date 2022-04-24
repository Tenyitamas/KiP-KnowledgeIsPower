package com.tenyitamas.kip_knowledgeispower.domain.preferences


interface Preferences {
    fun saveCountryCode(countryCode: String)


    fun loadCountryCode(): String

    companion object {
        const val KEY_COUNTRY_CODE = "country_code"
    }
}