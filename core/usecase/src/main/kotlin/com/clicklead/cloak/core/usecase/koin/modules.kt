package com.clicklead.cloak.core.usecase.koin

import com.clicklead.cloak.core.usecase.GetUrlUseCase

import com.clicklead.cloak.core.repository.koin.urlRepositoryModule
import org.koin.dsl.module


val useCaseModule = module {
    includes(urlRepositoryModule)
    factory { GetUrlUseCase(repository = get()) }
}

