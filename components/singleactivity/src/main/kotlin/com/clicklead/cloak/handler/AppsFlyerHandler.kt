package com.clicklead.cloak.handler

import android.util.Log
import com.appsflyer.AppsFlyerConversionListener
import com.appsflyer.attribution.AppsFlyerRequestListener
import com.appsflyer.deeplink.DeepLinkListener
import com.appsflyer.deeplink.DeepLinkResult
import com.clicklead.cloak.data.appsFlyer.InstallType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

object AppsFlyerHandler : AppsFlyerRequestListener, AppsFlyerConversionListener,
    DeepLinkListener {
    private val _installType: MutableStateFlow<InstallType?> = MutableStateFlow(null)
    val installType: StateFlow<InstallType?>
        get() = _installType
    private var priority = ""

    override fun onSuccess() {
        Log.d(
            "AppsFlyer.requestListener",
            "Launch sent successfully"
        )

    }

    override fun onError(p0: Int, p1: String) {
        Log.d(
            "AppsFlyer.requestListener", "Launch failed to be sent:\n" +
                    "Error code: $p0\n" + "Description:$p1"
        )
    }

    override fun onConversionDataSuccess(p0: MutableMap<String, Any>?) {
        Log.d("AppsFlyer.conversionDataListener", "CONVERSION SUCCESS")
        Log.d("AppsFlyer.conversionData", p0.toString())
        val companyName: String = (p0?.get("campaign")?.toString() ?: "")
        if (companyName.isEmpty()) {
            _installType.update {
                InstallType.ORGANIC(install = p0?.get("af_status")?.toString() ?: "organic")
            }
        } else {
            _installType.update {
                InstallType.NON_ORGANIC(
                    install = p0?.get("af_status")?.toString() ?: "Non_organic",
                    campaign = companyName,
                    mediaSource = (p0?.get("media_source")?.toString() ?: "")
                )
            }
        }
        priority = "conversion"
    }

    override fun onConversionDataFail(p0: String?) {
        Log.d("AppsFlyer.conversionDataListener", "CONVERSION DATA FAIL $p0")
    }

    override fun onAppOpenAttribution(attributionData: Map<String, String>) {
        Log.d("AppsFlyer.onAppOpenAttribution", "$attributionData")
    }

    override fun onAttributionFailure(errorMessage: String) {
        Log.d(
            "AppsFlyer.onAttributionFailure",
            "error onAttributionFailure : $errorMessage"
        )
    }

    override fun onDeepLinking(p0: DeepLinkResult) {
        if (priority != "conversion")
            if (p0.status == DeepLinkResult.Status.FOUND) {
                p0.deepLink.afSub1
                val companyName: String = (p0.deepLink.campaign ?: "")

                if (companyName.isEmpty()) {
                    _installType.update {
                        InstallType.ORGANIC(install = "organic")
                    }
                } else {
                    _installType.update {
                        InstallType.NON_ORGANIC(
                            install = "non_organic",
                            campaign = companyName,
                            mediaSource = p0.deepLink.mediaSource
                        )
                    }
                }

            }
    }
}