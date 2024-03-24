package com.clicklead.cloak

import android.app.Application
import com.appsflyer.AppsFlyerLib
import com.clicklead.cloak.handler.AppsFlyerHandler
import com.clicklead.cloak.koin.viewModelModule
import org.koin.core.context.startKoin
import org.koin.android.ext.koin.androidContext

class CloakApplication : Application() {
    override fun onCreate() {
        startKoin {
            androidContext(this@CloakApplication)
            modules(viewModelModule)
        }
        super.onCreate()
        AppsFlyerLib.getInstance().apply {
            subscribeForDeepLink(AppsFlyerHandler)
            init(BuildConfig.APPSFLYER_CODE_DEVELOPER, AppsFlyerHandler, applicationContext)
        }.start(this, BuildConfig.APPSFLYER_CODE_DEVELOPER, AppsFlyerHandler)
    }

}

