package com.clicklead.cloak.core.repository.koin

import com.clicklead.cloak.core.repository.api.UrlRepositoryI
import com.clicklead.cloak.core.repository.impl.UrlRepositoryImpl
import com.clicklead.cloak.core.urlservice.koin.serviceModule
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val urlRepositoryModule = module {
    includes(serviceModule)
    singleOf(::UrlRepositoryImpl) { bind<UrlRepositoryI>() }
}