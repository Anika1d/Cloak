package com.clicklead.cloak.core.client.koin

import com.clicklead.cloak.core.client.KtorClient
import org.koin.dsl.module

val httpClientModule = module { single { KtorClient.provideHttpClient()  } }