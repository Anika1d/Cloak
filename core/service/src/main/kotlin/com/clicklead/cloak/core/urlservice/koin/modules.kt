package com.clicklead.cloak.core.urlservice.koin

import com.clicklead.cloak.core.client.koin.httpClientModule
import com.clicklead.cloak.core.urlservice.api.UrlServiceI
import com.clicklead.cloak.core.urlservice.impl.UrlServiceImpl

import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.bind
import org.koin.dsl.module


val serviceModule = module {
    includes(httpClientModule)
    singleOf(::UrlServiceImpl) {
        bind<UrlServiceI>()
    }
}

