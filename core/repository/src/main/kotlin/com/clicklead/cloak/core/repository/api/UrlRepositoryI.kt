package com.clicklead.cloak.core.repository.api

import com.clicklead.cloak.core.data.request.InstallTypeDto
import com.clicklead.cloak.core.data.response.RawLinkDTO
import com.clicklead.cloak.core.data.resultstate.ResultState
import kotlinx.coroutines.flow.Flow

interface UrlRepositoryI {
    suspend fun getUrl(installTypeDto: InstallTypeDto): Flow<ResultState<RawLinkDTO>>
}