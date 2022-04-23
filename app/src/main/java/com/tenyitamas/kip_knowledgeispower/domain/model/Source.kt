package com.tenyitamas.kip_knowledgeispower.domain.model

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
data class Source(
    val id: String?,
    val name: String
) : Serializable, Parcelable

