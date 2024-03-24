package com.clicklead.cloak.core.repository.impl

import com.clicklead.cloak.core.data.request.InstallTypeDto
import com.clicklead.cloak.core.data.response.RawLinkDTO
import com.clicklead.cloak.core.data.resultstate.ResultState
import com.clicklead.cloak.core.repository.api.UrlRepositoryI
import com.clicklead.cloak.core.urlservice.api.UrlServiceI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import io.ktor.client.call.*

class UrlRepositoryImpl(private val service: UrlServiceI) : UrlRepositoryI {
    override suspend fun getUrl(installTypeDto: InstallTypeDto): Flow<ResultState<RawLinkDTO>> = flow{
        try {
            val response = service.getUrl(installTypeDto)
            emit(ResultState.Success(response.body<RawLinkDTO>()))
        } catch (e: Exception) {
            emit(ResultState.Error(e))
        }
    }.flowOn(Dispatchers.Default)
}