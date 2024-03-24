package com.clicklead.cloak.core.data.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RawLinkDTO(
    @SerialName("site") val site: String,
)