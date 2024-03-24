package com.clicklead.cloak.data.appsFlyer

sealed class InstallType(open val install: String){
    data class ORGANIC(
        override val install: String   //("af_status")
    ) : InstallType(install)

    data class NON_ORGANIC(
        override val install: String,   //("af_status")
        val mediaSource: String?,
        val campaign: String?,
    ) : InstallType(install)

}

