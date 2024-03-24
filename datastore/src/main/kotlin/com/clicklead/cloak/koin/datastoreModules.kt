package com.clicklead.cloak.koin

import com.clicklead.cloak.manager.impl.ManagerDataStoreImpl
import com.clicklead.cloak.manager.api.ManagerDataStoreI
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val managerDataStoreModule = module {
    singleOf(::ManagerDataStoreImpl) { bind<ManagerDataStoreI>() }
}