package com.clicklead.cloak.koin

import com.clicklead.cloak.core.usecase.koin.useCaseModule
import com.clicklead.cloak.viewmodel.MainViewModel
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val viewModelModule = module {
    includes(managerDataStoreModule)
    includes(useCaseModule)
    singleOf(::MainViewModel) {
        bind()
    }
}