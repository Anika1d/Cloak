package com.clicklead.cloak.core.data.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class InstallTypeDto(
    @SerialName("af_status") val install: String
)