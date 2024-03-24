package com.clicklead.cloak.rawlink

object RawLinkCreator {
    fun create(
        link: String,
        values: List<String>,
        keys: List<String> = values,
    ): String {
        var rawLink = link
        if (link[link.lastIndex] != '?' && values.isNotEmpty()) {
            rawLink += '?'
        }
        for (i in 0..values.lastIndex) {
            rawLink += "${keys[i]}=${values[i]}"
            if (i != values.lastIndex)
                rawLink += "&"
        }
        return rawLink
    }
}